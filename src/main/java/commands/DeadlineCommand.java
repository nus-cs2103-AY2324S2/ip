package commands;

import exception.MalformedUserInputException;
import tasklist.Deadline;

import java.util.regex.Pattern;

import static common.DateTimeHandler.DATE_INPUT_FORMAT_STRING;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = "\t " + COMMAND_WORD + ": Adds an deadline. \n"
            + "\t Example: " + COMMAND_WORD
            + " boil hot water /by " + DATE_INPUT_FORMAT_STRING ;

    public static final String MESSAGE_BLANK_EVENT = "\t The event name cannot be empty!";
    public static final String MESSAGE_BLANK_END_TIME = "\t The end time cannot be empty!";

    public static final String MESSAGE_SUCCESS = "\t Got it. I've added this task: \n" +
            "\t %s\n" +
            "\t Now you have %d task(s) available!";

    public static final Pattern DEADLINE_ARGUMENTS_FORMAT = Pattern.compile("(?<eventName>.+) \\/by (?<endTime>\\S+)");

    private final Deadline toAdd;

    public DeadlineCommand(String eventName, String by) throws MalformedUserInputException {
        this.toAdd = new Deadline(eventName, by, false);
    }

    @Override
    public CommandResult execute() {
        dataStorage.addTask(this.toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.toString(), dataStorage.getTaskCount()));
    }
}
