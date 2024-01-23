public class Deadline extends Task {

    private String byDate;

    public Deadline(String name, String byDate) {
        super(name);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate + ")";
    }
}
