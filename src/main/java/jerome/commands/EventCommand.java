package jerome.commands;

import static jerome.common.DateTimeHandler.DATE_INPUT_FORMAT_STRING;

import java.util.regex.Pattern;

import jerome.exception.MalformedUserInputException;
import jerome.tasklist.Event;


/**
 * Represents the command to add an event to the data storage.
 * @@author se-edu
 * Reuse from https://github.com/se-edu/addressbook-level2
 * with minor modifications to cater for differences in
 * error handling and output.
 */
public class EventCommand extends Command {
    /**
     * Represents the command word for the Event command.
     */
    public static final String COMMAND_WORD = "event";

    /**
     * Represents the usage message for the Event command.
     * Usage Example: event event_name /from 2021-02-25 /to 2024-01-01.
     */
    public static final String MESSAGE_USAGE = "\t " + COMMAND_WORD + ": Adds an event.\n"
            + "\t Example: " + COMMAND_WORD
            + " go for CS2103 tutorial /from " + DATE_INPUT_FORMAT_STRING + " /to " + DATE_INPUT_FORMAT_STRING;

    /**
     * Represents the message after the event is successfully added.
     * Contains the added event details and the current number of tasks in the database.
     */
    public static final String MESSAGE_SUCCESS = "\t Got it. I've added this task:\n"
            + "\t %s\n"
            + "\t Now you have %d task(s) available!";

    /**
     * Represents error message when the event name is blank.
     */
    public static final String MESSAGE_BLANK_EVENT = "\t The event name cannot be empty!";

    /**
     * Represents error message when the start time of an event is blank.
     */
    public static final String MESSAGE_BLANK_START_TIME = "\t The start time cannot be empty!";

    /**
     * Represents the error message when the end time of an event is blank.
     */
    public static final String MESSAGE_BLANK_END_TIME = "\t The end time cannot be empty!";

    /**
     * Represents the format of the arguments for a deadline command.
     * Regex to capture the `eventName`, `startTime` and `endTime` as a capture group.
     */
    public static final Pattern EVENT_ARGUMENTS_FORMAT =
            Pattern.compile("(?<eventName>.+) \\/from (?<startTime>\\S+) \\/to (?<endTime>\\S+)");

    /**
     * Represents the Event object that is to be stored.
     */
    private final Event toAdd;


    /**
     * Represents the EventCommand, which adds an event task to the data storage.
     *
     * @param eventName the name of the event.
     * @param startTime time that the event starts.
     * @param  endTime time that the end ends.
     * @throws MalformedUserInputException if the user input is dirty.
     */
    public EventCommand(String eventName, String startTime, String endTime) throws MalformedUserInputException {

        if (eventName.isBlank()) {
            throw new MalformedUserInputException(MESSAGE_BLANK_EVENT);
        }
        this.toAdd = new Event(eventName.trim(), startTime, endTime, false);
    }

    /**
     * Executes the command by adding a task to the data storage and returning a command result.
     * @return CommandResult object containing success message and task count in the custom format above.
     */
    @Override
    public CommandResult execute() {
        dataStorage.addTaskToTextFile(this.toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.toString(), dataStorage.getTaskCount()));
    }
}
