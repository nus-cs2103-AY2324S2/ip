package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Deadline class represents a task with a specified deadline.
 * It extends the Task class and provides additional functionality
 * for managing deadlines.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a Deadline instance with the specified task description, completion status, and deadline.
     *
     * @param taskDescription The description of the deadline task.
     * @param isCompleted     The completion status of the task.
     * @param by              The deadline for the task.
     */
    public Deadline(String taskDescription, boolean isCompleted, LocalDateTime by) {
        super(taskDescription, isCompleted);
        this.by = by;
    }

    /**
     * Gets the trimmed description of the deadline task.
     *
     * @return The trimmed description of the deadline task.
     */
    public String getDeadlineDescription() {
        return trimDescription(taskDescription);
    }

    /**
     * Gets the deadline date and time for the task.
     *
     * @return The deadline date and time for the task.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Gets the task icon associated with deadlines.
     *
     * @return The task icon for deadlines.
     */
    @Override
    public String getTaskIcon() {
        return "[D]";
    }

    /**
     * Gets the status icon based on the completion status of the task.
     *
     * @return The status icon for the task.
     */
    @Override
    public String getStatusIcon() {
        return isCompleted ? "[X]" : "[ ]";
    }

    /**
     * Gets the formatted task description, including the deadline.
     *
     * @return The formatted task description with the deadline.
     */
    @Override
    public String getTaskDescription() {
        return trimDescription(taskDescription) + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Trims the task description and extracts the deadline information.
     *
     * @param taskDescription The original task description.
     * @return The trimmed task description after extracting the deadline information.
     */
    @Override
    protected String trimDescription(String taskDescription) {
        String regex = "(?i)deadline\\s*(.*?)\\s*/by\\s*(.*?)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(taskDescription);

        if (matcher.matches()) {
            taskDescription = matcher.group(1).trim();
            by = LocalDateTime.parse(matcher.group(2),
                    DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        }

        return taskDescription;
    }

    @Override
    public TaskType getType() {
        return TaskType.DEADLINE;
    }
}
