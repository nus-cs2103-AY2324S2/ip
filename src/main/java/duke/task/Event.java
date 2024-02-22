package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Event class represents a task with a specified start and end time.
 * It extends the Task class and provides additional functionality
 * for managing events.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event instance with the specified task description, completion status, start time, and end time.
     *
     * @param taskDescription The description of the event task.
     * @param isCompleted     The completion status of the task.
     * @param from            The start time of the event.
     * @param to              The end time of the event.
     */
    public Event(String taskDescription, boolean isCompleted, LocalDateTime from, LocalDateTime to) {
        super(taskDescription, isCompleted);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the trimmed description of the event task.
     *
     * @return The trimmed description of the event task.
     */
    public String getEventDescription() {
        return trimDescription(taskDescription);
    }

    /**
     * Gets the task icon associated with events.
     *
     * @return The task icon for events.
     */
    @Override
    public String getTaskIcon() {
        return "[E]";
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
     * Gets the formatted task description, including the start and end times of the event.
     *
     * @return The formatted task description with the start and end times.
     */
    @Override
    public String getTaskDescription() {
        return trimDescription(taskDescription) + " (from: " +
                from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Trims the task description and extracts the start and end times of the event.
     *
     * @param taskDescription The original task description.
     * @return The trimmed task description after extracting the start and end times.
     */
    @Override
    protected String trimDescription(String taskDescription) {
        String regex = "(?i)event\\s*(.*?)\\s*/from\\s*(.*?)\\s*/to\\s*(.*?)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(taskDescription);

        if (matcher.matches()) {
            taskDescription = matcher.group(1).trim();
            String fromString = matcher.group(2).trim();
            String toString = matcher.group(3).trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            from = LocalDateTime.parse(fromString, formatter);
            to = LocalDateTime.parse(toString, formatter);
        }

        return taskDescription;
    }


    @Override
    public TaskType getType() {
        return TaskType.EVENT;
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDateTime getTo() {
        return this.to;
    }
}
