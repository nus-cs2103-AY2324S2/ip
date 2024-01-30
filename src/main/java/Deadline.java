public class Deadline extends Task {
    protected String by;


    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("%s%s%s",
         "[D]",
         super.toString(),
         " (by: " + this.by + ")");
    }
    
}
