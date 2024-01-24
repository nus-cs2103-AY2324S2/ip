public class Deadline extends Task {
    String type = "[D]";
    String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return this.type + this.display + " " + this.description + "(by: " + this.deadline.replaceAll("\\s+$", "") + ")";
    }
}