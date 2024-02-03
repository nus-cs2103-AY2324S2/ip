import java.io.Serializable;

public class Deadline extends Task implements Serializable {
    private String deadline;
    private static final long serialVersionUID = 3L;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + (isDone ? "X" : " ") + "] " + description + "(by: "
                + deadline + ")";
    }
}
