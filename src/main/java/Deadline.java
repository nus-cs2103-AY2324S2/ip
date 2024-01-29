import java.util.Objects;

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

    @Override
    public String toDBString() {
        String display;
        if (Objects.equals(this.display, "[ ]")) {
            display = "0";
        } else {
            display = "1";
        }
        return "T|" + display + "|" + this.description + "|" + this.deadline;
    }
}