package maltese.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import maltese.action.Echo;
import maltese.action.TaskList;
import maltese.exception.DuplicateTaskException;
import maltese.exception.EmptyDescriptionException;
import maltese.exception.InvalidDeadlineFormatException;
import maltese.exception.MalteseException;
import maltese.exception.WrongDateFormatException;


/**
 * Represents a task with a deadline in the maltese application.
 */
public class Deadline extends Task {
    private static final int DEADLINE_START_INDEX = 9;
    private static final int NUMBER_OF_SPLITS = 2;

    /**
     * The deadline date of the task.
     */
    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and deadline date.
     *
     * @param description The description of the task.
     * @param by The deadline date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the deadline date of the task.
     *
     * @return The deadline date.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Gets the type code representing the task type.
     *
     * @return A string representing the task type code.
     */
    public String getType() {
        return "D";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;
            return this.getDescription().equals(deadline.getDescription()) && this.getBy().equals(deadline.getBy());
        }
        return false;
    }
    /**
     * Parses the command for adding a deadline task.
     *
     * @param command  The command string containing the description and deadline of the task.
     * @param taskList The TaskList containing tasks to which the deadline task will be added.
     * @return An Echo action representing the response message after adding the deadline task.
     * @throws MalteseException If the command is invalid or if there are errors in parsing or adding the task.
     */
    public static Echo parse(String command, TaskList taskList) throws MalteseException {
        if (!command.contains("/by")) {
            throw new InvalidDeadlineFormatException();
        }

        String[] words = command.split(" ");
        if (words.length > 1) {
            try {
                String[] parts = command.split("/by", NUMBER_OF_SPLITS);
                String description = parts[0].substring(DEADLINE_START_INDEX).trim();
                if (description.isEmpty()) {
                    throw new EmptyDescriptionException();
                }
                LocalDate by = LocalDate.parse(parts[1].trim());
                Deadline deadline = new Deadline(description, by);
                if (taskList.contains(deadline)) {
                    throw new DuplicateTaskException();
                } else {
                    taskList.addTask(deadline);
                    return new Echo("Got it. I've added this task:\n  " + deadline + "\nNow "
                            + "you have " + taskList.size() + " tasks in the list.");
                }
            } catch (DateTimeParseException e) {
                throw new WrongDateFormatException();
            }
        } else {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Converts the task to a string representation.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.getDayOfMonth()
                + " " + by.getMonth() + " " + by.getYear() + ")";

    }
}
