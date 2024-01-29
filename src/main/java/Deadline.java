public class Deadline extends Task {

    private static final String TYPE = "D";

    protected String dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ":::" + this.dueDate;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString() + " (by: " + dueDate + ")";
    }

}
