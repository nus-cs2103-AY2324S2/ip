package helperpackage;

public class Deadline extends Task {
    protected String deadline;
    
    public Deadline(String description) throws DukeException {
        super(description.substring(0, description.indexOf("/")));
        String deadline = description.substring(description.indexOf("/")).replace("/by", "by:");

        if (!deadline.contains("by:")) {
            throw new DukeException("Invalid deadline input. :(");
        }
        
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" +  deadline + ")";
    }
}