package nollid.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import nollid.Parser;
import nollid.Storage;
import nollid.TaskList;
import nollid.exceptions.EmptyDescriptionException;
import nollid.exceptions.InvalidArgumentException;
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
            "Usage: event [task description] /from [d/m/yyyy] {hh:mm 24hr format} " + "/to [d/m/yyyy] {hh:mm 24hr "
                    + "format}";

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
        int fromIndex = this.argsList.indexOf("/from");
        int toIndex = this.argsList.indexOf("/to");

        checkDescriptionNotEmpty(fromIndex, toIndex);
        checkStartNotEmpty(fromIndex, toIndex);
        checkEndNotEmpty(fromIndex, toIndex);

        StringBuilder taskDescription = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();

        // Deal with the user sending "/from" before "/to" or vice versa
        if (fromIndex < toIndex) {
            extractEventInfo(this.argsList, fromIndex, toIndex, taskDescription, from, to);
        } else {
            extractEventInfo(this.argsList, toIndex, fromIndex, taskDescription, to, from);
        }

        LocalDateTime fromDateTime;
        LocalDateTime toDateTime;
        try {
            fromDateTime = Parser.getLocalDateTimeFromString(from.toString());
            toDateTime = Parser.getLocalDateTimeFromString(to.toString());
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("Unrecognized start/end format\n" + USAGE_HINT);
        }

        Event task = new Event(taskDescription.toString(), fromDateTime, toDateTime);
        tasks.add(task);
        String message = tasks.getAddSuccessMessage(task);
        storage.update(tasks);
        return message;
    }

    /**
     * Saves the appropriate data in the supplied StringBuilders, given the index of the '/from' and '/to' arguments
     * in the user input.
     */
    private void extractEventInfo(ArrayList<String> userInputAsList, int fromIndex, int toIndex,
                                  StringBuilder taskDescription, StringBuilder from, StringBuilder to) {
        for (int i = 1; i < fromIndex; i++) {
            if (i != fromIndex - 1) {
                taskDescription.append(userInputAsList.get(i)).append(" ");
            } else {
                taskDescription.append(userInputAsList.get(i));
            }
        }

        for (int i = fromIndex + 1; i < toIndex; i++) {
            if (i != toIndex - 1) {
                from.append(userInputAsList.get(i)).append(" ");
            } else {
                from.append(userInputAsList.get(i));
            }
        }

        for (int i = toIndex + 1; i < userInputAsList.size(); i++) {
            if (i != userInputAsList.size() - 1) {
                to.append(userInputAsList.get(i)).append(" ");
            } else {
                to.append(userInputAsList.get(i));
            }
        }
    }

    /**
     * Checks if the event description is provided.
     *
     * @param fromIndex The index of the "/from" argument.
     * @param toIndex   The index of the "/to" argument.
     * @throws EmptyDescriptionException If the event description is empty.
     */
    private void checkDescriptionNotEmpty(int fromIndex, int toIndex) throws EmptyDescriptionException {
        if (this.argsList.size() == 1 || fromIndex == 1 || toIndex == 1) {
            throw new EmptyDescriptionException("Event description cannot be empty!\n" + USAGE_HINT);
        }
    }

    /**
     * Checks if the start of the event is provided.
     *
     * @param fromIndex The index of the "/from" argument.
     * @param toIndex   The index of the "/to" argument.
     * @throws InvalidArgumentException If the event start is empty.
     */
    private void checkStartNotEmpty(int fromIndex, int toIndex) throws InvalidArgumentException {
        if (fromIndex == -1 || fromIndex == this.argsList.size() - 1 || fromIndex == toIndex - 1) {
            throw new InvalidArgumentException("Please enter the start of your event!\n" + USAGE_HINT);
        }
    }

    /**
     * Checks if the end of the event is provided.
     *
     * @param fromIndex The index of the "/from" argument.
     * @param toIndex   The index of the "/to" argument.
     * @throws InvalidArgumentException If the event start is empty.
     */
    private void checkEndNotEmpty(int fromIndex, int toIndex) throws InvalidArgumentException {
        if (toIndex == -1 || toIndex == this.argsList.size() - 1 || toIndex == fromIndex - 1) {
            throw new InvalidArgumentException("Please enter the end of your event!\n" + USAGE_HINT);
        }
    }
}
