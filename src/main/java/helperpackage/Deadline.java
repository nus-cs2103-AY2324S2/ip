package helperpackage;

public class Deadline extends Task {
    protected String deadline;
    
    public Deadline(String description) {
        super(description.substring(0, description.indexOf("/")));
        this.deadline = description.substring(description.indexOf("/")).replace("/by", "by:");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" +  deadline + ")";
    }
}