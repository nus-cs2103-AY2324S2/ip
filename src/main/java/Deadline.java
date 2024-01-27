public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + "(by: " + by + ")";
    }

    public String getBy() {
        return by;
    }

}
