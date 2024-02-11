package duke;

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
     * @param taskList     The task list to add the task to.
     * @param description  The description of the task.
     * @param dueBy        The due by date of the task.
     * @throws DukeException If there is an issue adding the task.
     */
    public static void addDeadlineTask(TaskList taskList, String description, String dueBy) throws DukeException{
        LocalDate dueByDate = parseDate(dueBy);
        taskList.addTask(new Deadline(description, dueByDate));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.getTask(taskList.size() - 1).getStatusIcon());
        System.out.println("Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? "" : "s") + " in the list.");
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