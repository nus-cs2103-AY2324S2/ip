public class Deadline extends Task {
    private final String type = "[D]";
    private String deadline;

    public Deadline(String description) {
        super(description.split("/")[0]);
        this.deadline = description.split("/by ")[1];
    }

    public String getType() {
        return this.type;
    }

    public String getExtraInfo() {
        return "(by: " + this.deadline + ")";
    }
}




