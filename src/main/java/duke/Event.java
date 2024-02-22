package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task.
 */
public class Event extends Task {

    protected LocalDate start;
    protected LocalDate end;

    /**
     * Creates an Event task with the given description, start, and end details.
     *
     * @param description The description of the task.
     * @param start The start details of the event.
     * @param end The end details of the event.
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Adds an Event task to the task list with the specified description, start, and end details.
     *
     * @param taskList The task list to add the task to.
     * @param description The description of the task.
     * @param start The start details of the event.
     * @param end The end details of the event.
     * @return A string representing the result of adding the Event task.
     * @throws GeorgieException If there is an issue adding the task.
     */
    public static String addEventTask(TaskList taskList, String description, String start, String end) throws GeorgieException {
        assert (start + " /to " + end).length() >= "event /from /to".length() : "Input not handled properly";

        LocalDate startDate = parseDate(start);
        LocalDate endDate = parseDate(end);
        taskList.addTask(new Event(description, startDate, endDate));

        int newSize = taskList.size();
        assert newSize > 0 : "Task not added successfully";

        String result = "Got it. I've added this task:\n" +
                taskList.getTask(taskList.size() - 1).getStatusIcon() + "\n" +
                "Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? "" : "s") + " in the list.";
        System.out.println(result);
        return result;
    }

    /**
     * Returns the string representation of the task's status and details.
     *
     * @return The formatted string representation.
     */
    @Override
    public String getStatusIcon() {
        return "[E] " + super.getStatusIcon() + " " + description + " (from: " +
                start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: " +
                end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns the string representation of the task for saving to a file.
     *
     * @return The formatted string for saving to a file.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " +
                start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " | " +
                end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }
}
