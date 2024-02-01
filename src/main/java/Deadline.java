public class Deadline extends Task {
    protected String by;
    public Deadline(String description) {
        super(description);
    }

    public Deadline(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + this.description;
    }

}
