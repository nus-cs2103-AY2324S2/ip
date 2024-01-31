public class Deadline extends Task{
    private String by;

    public Deadline(String name, Boolean status, String by) {
        super(name,status);
        this.by = by;
    }

    @Override public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
