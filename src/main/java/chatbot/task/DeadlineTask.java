package chatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {

    private LocalDateTime timing;

    /**
     * Constructor for DeadlineTask.
     * @param description The description of the task.
     * @param timing The timing of the task.
     */
    public DeadlineTask(String description, LocalDateTime timing) {
        super(description);
        this.timing = timing;
    }

    /**
     * Constructor for DeadlineTask.
     * @param description The description of the task.
     * @param timing The timing of the task.
     * @param isCompleted Whether the task is completed.
     */
    public DeadlineTask(String description, LocalDateTime timing, boolean isCompleted) {
        super(description, isCompleted);
        this.timing = timing;
    }

    @Override
    public String toString() {
        String status = super.isCompleted() ? "[X]" : "[ ]";
        String timingString = this.timing.format(DateTimeFormatter.ofPattern(Task.DATETIME_FORMAT_OUTPUT));
        return "[D]" + status + " " + super.getDescription() + " (by: " + timingString + ")";
    }

    @Override
    public String exportToSave() {
        String status = super.isCompleted() ? "1" : "0";
        return "D," + status + "," + super.getDescription() + "," + this.timing.toString();
    }

}
