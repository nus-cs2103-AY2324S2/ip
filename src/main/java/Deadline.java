public class Deadline extends Task {
    private String due;
    public Deadline(String taskName, boolean done, String due) {
        super(taskName, done);
        this.due = due;
    }

    @Override
    public String storeData() {
        return super.storeData() + " " + this.due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                String.format(" (by: %s)", due);
    }
}
