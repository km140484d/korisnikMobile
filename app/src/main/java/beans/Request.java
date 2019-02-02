package beans;


import java.util.Date;

public class Request implements Comparable<Request>{

    @Override
    public int compareTo(Request o) {
        if (requestCreatedDate.compareTo(o.getRequestCreatedDate()) < 0)
            return -1;
        else{
            if (requestCreatedDate.compareTo(o.getRequestCreatedDate()) == 0)
                return 0;
        }
        return 1;
    }

    public enum RequestStates{
        POSLAT, OTKAZAN, ODBIJEN, REALIZOVAN, OCENJEN
    }

    public class Address{
        private String county;
        private String streetNumber;

        public Address(String county, String streetNumber) {
            this.county = county;
            this.streetNumber = streetNumber;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getStreetNumber() {
            return streetNumber;
        }

        public void setStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
        }
    }

    private static int stId = 0;
    private int id = ++stId;
    private Handyman handyman;
    private Handyman.Job job;
    private Address address;
    private Date requestCreatedDate;
    private Date requestDate;
    private Double urgency;
    private Boolean cash;
    private RequestStates currentState = RequestStates.POSLAT;
    private float rating;
    private String comment;
    private Date canceledDate;

    public Request(Handyman handyman, Handyman.Job job, String county, String streetNumber,
                   Date requestCreatedDate, Date requestDate, Double urgency,
                   Boolean cash) {
        this.handyman = handyman;
        this.job = job;
        this.address = new Address(county, streetNumber);
        this.requestCreatedDate = requestCreatedDate;
        this.requestDate = requestDate;
        this.urgency = urgency;
        this.cash = cash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Handyman getHandyman() {
        return handyman;
    }

    public void setHandyman(Handyman handyman) {
        this.handyman = handyman;
    }

    public Handyman.Job getJob() {
        return job;
    }

    public void setJob(Handyman.Job job) {
        this.job = job;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getRequestCreatedDate() {
        return requestCreatedDate;
    }

    public void setRequestCreatedDate(Date requestCreatedDate) {
        this.requestCreatedDate = requestCreatedDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Double getUrgency() {
        return urgency;
    }

    public void setUrgency(Double urgency) {
        this.urgency = urgency;
    }

    public Boolean isCash() {
        return cash;
    }

    public void setCash(Boolean cash) {
        this.cash = cash;
    }

    public RequestStates getCurrentState() {
        return currentState;
    }

    public void setCurrentState(RequestStates currentState) {
        this.currentState = currentState;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCanceledDate() {
        return canceledDate;
    }

    public void setCanceledDate(Date canceledDate) {
        this.canceledDate = canceledDate;
    }
}
