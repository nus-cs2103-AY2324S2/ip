package duke.commands;

import duke.exception.MalformedUserInputException;
import duke.tasklist.Event;

import java.util.regex.Pattern;

import static duke.common.DateTimeHandler.DATE_INPUT_FORMAT_STRING;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = "\t " + COMMAND_WORD + ": Adds an event.\n"
            + "\t Example: " + COMMAND_WORD
            + " go for CS2103 tutorial /from " + DATE_INPUT_FORMAT_STRING + " /to " + DATE_INPUT_FORMAT_STRING;

    public static final String MESSAGE_SUCCESS = "\t Got it. I've added this task:\n" +
            "\t %s\n" +
            "\t Now you have %d task(s) available!";

    public static final String MESSAGE_BLANK_EVENT = "\t The event name cannot be empty!";
    public static final String MESSAGE_BLANK_START_TIME = "\t The start time cannot be empty!";
    public static final String MESSAGE_BLANK_END_TIME = "\t The end time cannot be empty!";

    public static final Pattern EVENT_ARGUMENTS_FORMAT = Pattern.compile("(?<eventName>.+) \\/from (?<startTime>\\S+) \\/to (?<endTime>\\S+)");

    private final Event toAdd;

    public EventCommand(String eventName, String startTime, String endTime) throws MalformedUserInputException {
        this.toAdd = new Event(eventName, startTime, endTime, false);
    }

    @Override
    public CommandResult execute() {
        dataStorage.addTask(this.toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.toString(), dataStorage.getTaskCount()));
    }
}
