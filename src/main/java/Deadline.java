public class Deadline extends Task {

    private String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), this.description, this.deadline);
    }
    @Override
    public String getSaveLine() {
        return "D " + (this.isDone ? "1 " : "0 ") + this.description + " /by " + this.deadline + "\n";
    }
}
