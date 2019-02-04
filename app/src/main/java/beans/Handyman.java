package beans;

import java.util.*;

public class Handyman extends User{
    private static int jobStId = 0;

    public class Job{
        private int id = ++jobStId;
        private Occupation occupation;
        private Double price;
        private Integer experience;
        private float rating;

        public Job(Occupation occupation, Double price, Integer experience, float rating) {
            this.occupation = occupation;
            this.price = price;
            this.experience = experience;
            this.rating = rating;
        }

        public Occupation getOccupation() {
            return occupation;
        }

        public void setOccupation(Occupation occupation) {
            this.occupation = occupation;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Integer getExperience() {
            return experience;
        }

        public void setExperience(Integer experience) {
            this.experience = experience;
        }

        public float getRating() {
            return rating;
        }

        public void setRating(float rating) {
            this.rating = rating;
        }
    }

    private List<Skill> skills = new ArrayList<>();
    private List<Job> jobs = new ArrayList<>();
    private List<Request> requests = new ArrayList<>();

    public Handyman(String name, String surname, String phone, String email, List<Skill> skills) {
        super(name, surname, phone, email);
        this.skills = skills;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public void addRequest(Request request){
        requests.add(0, request);
    }

    public List<Request> getRequests() {
        return requests;
    }

    public String jobsToString(){
        String str = "";
        for(Job job: jobs){
            str = (str.isEmpty() ? "" : str + ", ");
            str += job.getOccupation().getWork();
        }
        return str;
    }

    public float getAverageRating(){
        float avg = 0.0f;
        for(Job job: jobs)
            avg += job.getRating();
        return avg / jobs.size();
    }
}
