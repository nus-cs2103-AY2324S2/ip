package kwuntalk.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dueDateTime;


    /**
     * Constructor for a deadline task.
     *
     * @param task Task description.
     * @param dueDateTime DateTime of deadline.
     */
    public Deadline(String task, LocalDateTime dueDateTime) {
        super(task);
        this.dueDateTime = dueDateTime;
    }


    /**
     * Format task to store in the Tasks File.
     *
     * @return String representation of the formatted task.
     */
    @Override
    public String formatTask() {
        String status = getStatus() ? "1" : "0";
        return String.format("D | %s | %s | %s", status, super.formatTask(), dueDateTime);
    }


    /**
     * Return the string representation of the task.
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                dueDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")));
    }
}
