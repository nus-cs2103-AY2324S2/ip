package georgie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDate dueByDate;

    /**
     * Creates a Deadline task with the given description and due by date.
     *
     * @param description The description of the task.
     * @param dueByDate   The due by date of the task.
     */
    public Deadline(String description, LocalDate dueByDate) {
        super(description);
        this.dueByDate = dueByDate;
    }

    /**
     * Adds a Deadline task to the task list with the specified description and due by date.
     *
     * @param taskList The task list to add the task to.
     * @param description The description of the task.
     * @param dueBy The due by date of the task.
     * @return A string representing the result of adding the Deadline task.
     * @throws GeorgieException If there is an issue adding the task.
     */
    public static String addDeadlineTask(TaskList taskList, String description, String dueBy) throws GeorgieException {
        assert (description + " /by " + dueBy).length() >= "deadline /by yyyy-MM-dd HH:mm".length() : "Input not handled properly";

        LocalDate dueByDate = parseDate(dueBy);

        if (dueByDate == null) {
            throw new GeorgieException("Oops! Please provide valid due date with format /by 'yyyy-MM-dd'.");
        }

        taskList.addTask(new Deadline(description, dueByDate));

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
        return "[D] " + super.getStatusIcon() + " " + description + " (by: " +
                dueByDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns the string representation of the task for saving to a file.
     *
     * @return The formatted string for saving to a file.
     */
    @Override
    public String toFileString() {
        if (dueByDate == null) {
            return "";
        }

        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " +
                dueByDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Parses the date from the given date string.
     *
     * @param dateString The date string to parse.
     * @return The parsed LocalDate.
     */
    public static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }
}