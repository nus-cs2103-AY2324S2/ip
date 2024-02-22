package nollid.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import nollid.Parser;
import nollid.Storage;
import nollid.TaskList;
import nollid.exceptions.EmptyDescriptionException;
import nollid.exceptions.InvalidArgumentException;
import nollid.exceptions.MissingTagsException;
import nollid.exceptions.NollidException;
import nollid.tasks.Event;

/**
 * EventCommand class represents a command for adding an Event task.
 * It extends the Command class and implements the execute method to perform the command logic.
 */
public class EventCommand extends Command {
    /**
     * Constant string providing usage hint for the EventCommand.
     */
    public static final String USAGE_HINT =
            "Usage: event task_description /from d/m/yyyy [hh:mm] /to d/m/yyyy [hh:mm] [/tags tag1,tag2,...]";
    /**
     * ArrayList containing command arguments.
     */
    private final ArrayList<String> argsList;

    /**
     * Constructor for EventCommand.
     *
     * @param argsList ArrayList containing command arguments.
     */
    public EventCommand(ArrayList<String> argsList) {
        this.argsList = argsList;
    }

    /**
     * Overrides the execute method from the Command class.
     * Executes the command to add an event task.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws NollidException {
        String taskDescription;
        try {
            taskDescription = Parser.getDescription(argsList);
        } catch (EmptyDescriptionException e) {
            throw new EmptyDescriptionException(e.getMessage() + "\n" + USAGE_HINT);
        }

        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();
        try {
            extractEventStartEnd(from, to);
        } catch (InvalidArgumentException e) {
            throw new InvalidArgumentException(e.getMessage() + "\n" + USAGE_HINT);
        }

        LocalDateTime fromDateTime;
        LocalDateTime toDateTime;
        try {
            fromDateTime = Parser.getLocalDateTimeFromString(from.toString());
            toDateTime = Parser.getLocalDateTimeFromString(to.toString());
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("Unrecognized start/end format\n" + USAGE_HINT);
        }

        ArrayList<String> tags;
        try {
            tags = Parser.getTags(argsList);
        } catch (MissingTagsException e) {
            throw new MissingTagsException(e.getMessage() + "\n" + USAGE_HINT);
        }

        Event task = new Event(taskDescription, fromDateTime, toDateTime, tags);
        tasks.add(task);
        storage.update(tasks);
        return tasks.getAddSuccessMessage(task);
    }

    /**
     * Extracts the start and end date strings from the input arguments.
     *
     * @param fromString The StringBuilder to store the start date string.
     * @param toString   The StringBuilder to store the end date string.
     * @throws InvalidArgumentException If the start or end date is empty.
     */
    private void extractEventStartEnd(StringBuilder fromString,
                                      StringBuilder toString) throws InvalidArgumentException {
        int fromIndex = this.argsList.indexOf("/from");
        int toIndex = this.argsList.indexOf("/to");

        for (int i = fromIndex + 1; i < this.argsList.size(); i++) {
            if (!this.argsList.get(i).matches(Parser.OPTION_REGEX)) {
                fromString.append(this.argsList.get(i)).append(" ");
            } else {
                break;
            }
        }

        if (fromString.length() == 0) {
            throw new InvalidArgumentException("Start date cannot be empty.");
        }

        for (int i = toIndex + 1; i < this.argsList.size(); i++) {
            if (!this.argsList.get(i).matches(Parser.OPTION_REGEX)) {
                toString.append(this.argsList.get(i)).append(" ");
            } else {
                break;
            }
        }

        if (toString.length() == 0) {
            throw new InvalidArgumentException("End date cannot be empty.");
        }
    }
}
