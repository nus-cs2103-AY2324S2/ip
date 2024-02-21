package maltese.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import maltese.action.Echo;
import maltese.action.TaskList;
import maltese.exception.DuplicateTaskException;
import maltese.exception.EmptyDescriptionException;
import maltese.exception.InvalidEventFormatException;
import maltese.exception.MalteseException;
import maltese.exception.WrongDateFormatException;
import maltese.exception.WrongDateOrderingException;


/**
 * Represents a task with an event in the maltese application.
 */
public class Event extends Task {
    private static final int EVENT_START_INDEX = 6;
    private static final int NUMBER_OF_SPLITS = 2;

    /**
     * The starting date of the event.
     */
    protected LocalDate from;

    /**
     * The ending date of the event.
     */
    protected LocalDate to;

    /**
     * Constructs an Event task with the specified description, starting date, and ending date.
     *
     * @param description The description of the event.
     * @param from The starting date of the event.
     * @param to The ending date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the type code representing the task type.
     *
     * @return A string representing the task type code.
     */
    public String getType() {
        return "E";
    }

    /**
     * Gets the starting date of the event.
     *
     * @return The starting date.
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Gets the ending date of the event.
     *
     * @return The ending date.
     */
    public LocalDate getTo() {
        return this.to;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            return this.getDescription().equals(event.getDescription()) && this.getTo().equals(event.getTo())
                    && this.getFrom().equals(event.getFrom());
        }
        return false;
    }

    /**
     * Parses the command for adding a deadline task.
     *
     * @param command  The command string containing the description and deadline of the task.
     * @param taskList The TaskList containing tasks to which the deadline task will be added.
     * @return An Echo action representing the response message after adding the event task.
     * @throws MalteseException If the command is invalid or if there are errors in parsing or adding the task.
     */

    public static Echo parse(String command, TaskList taskList) throws MalteseException {
        String[] words = command.split(" ");
        if (words.length > 1 && command.contains("/from") && command.contains("/to")) {
            try {
                String[] parts = command.split("/from", NUMBER_OF_SPLITS);
                String description = parts[0].substring(EVENT_START_INDEX).trim();
                if (description.isEmpty()) {
                    throw new EmptyDescriptionException();
                }
                String[] eventDetails = parts[1].split("/to", NUMBER_OF_SPLITS);
                LocalDate from = LocalDate.parse(eventDetails[0].trim());
                LocalDate to = LocalDate.parse(eventDetails[1].trim());
                if (to.isBefore(from)) {
                    throw new WrongDateOrderingException();
                }
                Event event = new Event(description, from, to);
                if (taskList.contains(event)) {
                    throw new DuplicateTaskException();
                } else {
                    taskList.addTask(event);
                    return new Echo("Got it. I've added this task:\n  " + event
                            + "\nNow you have " + taskList.size() + " tasks in the list.");
                }
            } catch (DateTimeParseException e) {
                throw new WrongDateFormatException();
            }
        } else {
            throw new InvalidEventFormatException();
        }
    }

    /**
     * Converts the task to a string representation.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.getDayOfMonth()
                + " " + from.getMonth() + " " + from.getYear() + " to: " + to.getDayOfMonth()
                + " " + to.getMonth() + " " + to.getYear() + ")";
    }
}
