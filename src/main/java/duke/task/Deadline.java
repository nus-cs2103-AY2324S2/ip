package duke.task;


import duke.action.Echo;
import duke.action.TaskList;
import duke.exception.DukeException;
import duke.exception.DuplicateTaskException;
import duke.exception.EmptyDescriptionException;
import duke.exception.WrongDateFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;



/**
 * Represents a task with a deadline in the Duke application.
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

    public static Echo parse(String command, TaskList taskList) throws DukeException {
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
