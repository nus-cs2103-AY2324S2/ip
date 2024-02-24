package jerome.commands;

import static jerome.common.DateTimeHandler.DATE_INPUT_FORMAT_STRING;

import java.util.regex.Pattern;

import jerome.exception.MalformedUserInputException;
import jerome.tasklist.Deadline;


/**
 * Represents the command to add a deadline task to the data storage.
 *
 * @@author se-edu
 * Reuse from https://github.com/se-edu/addressbook-level2
 * with modifications to the format.
 */
public class DeadlineCommand extends Command {
    /**
     * Represents the command word for the Deadline command.
     */
    public static final String COMMAND_WORD = "deadline";

    /**
     * Represents the usage message for the Deadline command.
     * Usage Example: deadline boil hot water /by 2021-07-30.
     */
    public static final String MESSAGE_USAGE = "\t " + COMMAND_WORD + ": Adds an deadline.\n"
            + "\t Example: " + COMMAND_WORD
            + " boil hot water /by " + DATE_INPUT_FORMAT_STRING;

    /**
     * Represents the error message when the event name is blank.
     */
    public static final String MESSAGE_BLANK_EVENT = "\t Error: The event name cannot be empty!\n" + MESSAGE_USAGE;

    /**
     * Represents the error message when the deadline's end time is blank.
     */
    public static final String MESSAGE_BLANK_END_TIME = "\t Error: The end time cannot be empty!\n" + MESSAGE_USAGE;

    /**
     * Represents the message after the deadline is successfully added.
     * Contains the added deadline details and the current number of tasks in the database.
     */
    public static final String MESSAGE_SUCCESS = "\t Got it. I've added this task: \n"
            + "\t %s\n"
            + "\t Now you have %d task(s) available!";

    /**
     * Represents the format of the arguments for a deadline command.
     * Regex to capture the `eventName` and `endTime` as a capture group.
     */
    public static final Pattern DEADLINE_ARGUMENTS_FORMAT = Pattern.compile("(?<eventName>.+) \\/by (?<endTime>\\S+)");

    /**
     * Represents the Deadline object that is to be stored.
     */
    private final Deadline toAdd;

    /**
     * Represents the DeadlineCommand, which adds a deadline task to the data storage.
     *
     * @param eventName the name of the deadline event.
     * @param by        the deadline's end time.
     * @throws MalformedUserInputException if the user input is dirty.
     */
    public DeadlineCommand(String eventName, String by) throws MalformedUserInputException {
        this.toAdd = new Deadline(eventName, by, false);
    }

    /**
     * Executes the command by adding a task to the data storage and returning a command result.
     *
     * @return CommandResult object containing success message and task count in the custom format above.
     */
    @Override
    public CommandResult execute() {
        dataStorage.addTaskToTextFile(this.toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.toString(), dataStorage.getTaskCount()));
    }
}
