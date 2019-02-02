package beans;

public class Occupation {

    private static int stId = 0;
    private int id = ++stId;
    private String work;

    public Occupation(String occupation){
        this.work = occupation;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
