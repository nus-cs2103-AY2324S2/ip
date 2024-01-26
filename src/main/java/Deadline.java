public class Deadline extends Task {

    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String toSavableFormat() {
        return this.uuid + "|D|" + this.description + "|" + this.done + "|" + deadline;
    }

    @Override
    public String getType() {
        return "[D]";
    }
}
