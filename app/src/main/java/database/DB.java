package database;

import beans.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DB {

    private static DB instanceDB;

    private static Calendar calendar = Calendar.getInstance();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Occupation> occupations = new ArrayList<>();
    private static List<Skill> skills = new ArrayList<>();
    private static List<Handyman> handymen = new ArrayList<>();
    private static List<Handyman.Job> jobs = new ArrayList<>();
    private static Customer currentCustomer;
    private static Handyman currentHandyman;

    private DB() {
    }

    public static DB getDBInstance() {
        if (instanceDB == null)
            instanceDB = new DB();
        return instanceDB;
    }

    static {
        //CUSTOMERS
        calendar.set(2020, Calendar.FEBRUARY, 1);
        Date date = calendar.getTime();
        currentCustomer = new Customer("Mirjana", "Konjikovac", "0612345678", "mirjanakonjikovac@gmail.com",
                "km140484d", "123", "Odgovaraju popodnevni časovi za popravke.");
        currentCustomer.setCreditCard(currentCustomer.new CreditCard("1234567891234567", date, "123", 10230.56));
        customers.add(currentCustomer);
        calendar.set(2019, Calendar.MAY, 1);
        date = calendar.getTime();
        currentCustomer = new Customer("Luka", "Savić", "0619599678", "lukasavic@yahoo.com",
                "sl140283d", "123", "Pozeljan rad vikendom.");
        currentCustomer.setCreditCard(currentCustomer.new CreditCard("9999567822224567", date, "111", 8561.23));
        customers.add(currentCustomer);
        currentCustomer = null;

        //OCCUPATIONS
        occupations.add(new Occupation("Električar")); //0
        occupations.add(new Occupation("Moler")); //1
        occupations.add(new Occupation("Stolar")); //2
        occupations.add(new Occupation("Vodoinstalater")); //3
        occupations.add(new Occupation("Tapetar")); //4

        //SKILLS
        skills.add(new Skill("Farbanje"));
        skills.add(new Skill("Krecenje"));
        skills.add(new Skill("Plocice"));
        skills.add(new Skill("Drvorez"));

        //HANDYMEN
        currentHandyman = new Handyman("Elena", "Vuceljić", "06911122330", "elena@hotmail.com",
                skills.subList(0, 1));
        jobs.add(currentHandyman.new Job(occupations.get(0), 2000.00, 5, 4));
        currentHandyman.setJobs(jobs);
        handymen.add(currentHandyman);

        currentHandyman = new Handyman("Milica", "Bulatović", "06922277335", "mica@gmail.com",
                skills.subList(0, 2));
        jobs = new ArrayList<>();
        jobs.add(currentHandyman.new Job(occupations.get(0), 1800.00, 8, 4));
        jobs.add(currentHandyman.new Job(occupations.get(1), 1300.00, 2, 3));
        jobs.add(currentHandyman.new Job(occupations.get(4), 1800.00, 4, 5));
        currentHandyman.setJobs(jobs);
        handymen.add(currentHandyman);

        currentHandyman = new Handyman("Dane", "Nikolić", "0645556667", "dane@gmail.com",
                skills.subList(2, 3));
        jobs = new ArrayList<>();
        jobs.add(currentHandyman.new Job(occupations.get(1), 1500.00, 6, 4));
        jobs.add(currentHandyman.new Job(occupations.get(2), 2100.00, 12, 5));
        currentHandyman.setJobs(jobs);
        handymen.add(currentHandyman);

        currentHandyman = new Handyman("Djordje", "Danicic", "0645556667", "djolence@gmail.com",
                skills.subList(2, 3));
        jobs = new ArrayList<>();
        jobs.add(currentHandyman.new Job(occupations.get(0), 2300.00, 1, 2));
        jobs.add(currentHandyman.new Job(occupations.get(2), 2500.00, 7, 5));
        currentHandyman.setJobs(jobs);
        handymen.add(currentHandyman);

        currentHandyman = new Handyman("Dragana", "Tanic", "0649988665", "dragce@gmail.com",
                skills.subList(1, 3));
        jobs = new ArrayList<>();
        jobs.add(currentHandyman.new Job(occupations.get(1), 1950.00, 4, 4));
        jobs.add(currentHandyman.new Job(occupations.get(3), 2300.00, 5, 5));
        currentHandyman.setJobs(jobs);
        handymen.add(currentHandyman);

        currentHandyman = null;
    }

    public static List<Customer> getCustomers() {
        return customers;
    }

    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public static List<Occupation> getOccupations() {
        return occupations;
    }

    public static List<Skill> getSkills() {
        return skills;
    }

    public static List<Handyman> getHandymen() {
        return handymen;
    }

    public static Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public static void setCurrentCustomer(Customer currentCustomer) {
        DB.currentCustomer = currentCustomer;
    }

    public static Handyman getCurrentHandyman() {
        return currentHandyman;
    }

    public static void setCurrentHandyman(Handyman currentHandyman) {
        DB.currentHandyman = currentHandyman;
    }

    public Handyman.Job findJob(String job) {
        for (Handyman.Job j : jobs) {
            if (j.getOccupation().getWork().equals(job))
                return j;
        }
        return null;
    }

    public Date currentDate() {
        calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Integer getRequestStateNumber(Request.RequestStates state) {
        List<Request> requests = currentCustomer.getRequests();
        for (Request request : requests)
            if (request.getCurrentState().equals(state))
                requests.add(request);
        return requests.size();
    }

    public static List<Request> getRequestArchive() {
        List<Request> requests = currentCustomer.getRequests();
        Collections.sort(requests);
        return (requests.size() > 3) ? requests.subList(0, 3) : requests;
    }
}
