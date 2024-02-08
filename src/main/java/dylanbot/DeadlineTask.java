package dylanbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task of type D == DeadlineTask
 */
public class DeadlineTask extends Task {
    private LocalDateTime deadline;
    private DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm:ss");

    /**
     * Creates a new DeadlineTask with the given description and deadline
     *
     * @param desc The specified description
     * @param deadline The specified deadline
     */
    public DeadlineTask(String desc, LocalDateTime deadline) {
        super("D", desc);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        String res = "";
        try {
            res = "[" + this.getType() + "] "
                    + (this.isCompleted() ? "[X]" : "[ ]")
                    + " " + this.getDesc()
                    + " (by: " + deadline.format(printFormat) + ")";
        } catch (NullPointerException e) {
            Ui.print(e.getMessage());
        }
        return res;
    }
}
