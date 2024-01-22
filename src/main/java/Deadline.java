public class Deadline extends Task{
    private String by;

    public Deadline(Boolean status, String detail, String by) {
        super(status, detail);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
