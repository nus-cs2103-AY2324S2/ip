public class Deadline extends Task {
    private final String TYPE = "D";
    private String deadline;

    public Deadline(String description) {
        super(description.split("/")[0]);
        this.deadline = description.split("/by ")[1];
    }

    public  Deadline(String event, String extraInfo) {
        super(event);
        this.deadline = extraInfo;
    }

    public String getType() {
        return this.TYPE;
    }
    public String getExtraInfoShortened() {
        return this.deadline;
    }


    public String getExtraInfo() {
        return "(by: " + this.deadline + ")";
    }

}




