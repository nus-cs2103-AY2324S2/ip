public class Deadlines extends Task{

    protected String by;
    public Deadlines(String task, String by) {
        super(task);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }

}
