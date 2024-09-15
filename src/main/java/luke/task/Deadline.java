package luke.task;

import java.time.LocalDateTime;

/**
 * A task that should be completed before its dueDate.
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;

    /**
     * Constructor of Deadline.
     * Uses the constructor of Task.
     * @param description The description of the task.
     * @param dueDate The date that the task should be completed before.
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        assert dueDate != null;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String month = String.valueOf(dueDate.getMonth());
        String monthInShortForm = month.charAt(0) + month.substring(1, 3).toLowerCase();
        String day = dueDate.getDayOfMonth() < 10
                ? "0" + dueDate.getDayOfMonth()
                : String.valueOf(dueDate.getDayOfMonth());
        String minute = dueDate.getMinute() < 10
                ? "0" + dueDate.getMinute()
                : String.valueOf(dueDate.getMinute());
        String hour = dueDate.getHour() < 10
                ? "0" + dueDate.getHour()
                : String.valueOf(dueDate.getHour());
        return "[D]" + super.toString() + " (by: " + day + " " + monthInShortForm
                + " " + dueDate.getYear() + " " + hour + ":" + minute + ")";
    }
}
