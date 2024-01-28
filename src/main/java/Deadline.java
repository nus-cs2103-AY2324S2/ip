public class Deadline extends Task {

    private String byDate;

    public Deadline(String name, String byDate) {
        super(name);
        this.byDate = byDate;
    }

    public Deadline(String name, String byDate, boolean isDone) {
        super(name, isDone);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate + ")";
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String[] getTimes() {
        return new String[] { byDate, "NILL" };
    }
}
