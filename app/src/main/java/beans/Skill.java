package beans;

public class Skill {

    private static int stId = 0;
    private int id = ++stId;
    private String description;

    public Skill(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
