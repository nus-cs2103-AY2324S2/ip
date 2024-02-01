public class Deadline extends Task {
    private String deadline;

    Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s) ", deadline);
    }
}
