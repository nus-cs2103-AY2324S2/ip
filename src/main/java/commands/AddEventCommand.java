package commands;

import exceptions.InvalidFormatException;
import exceptions.LeluException;
import tasks.Deadline;
import tasks.Event;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * This class represents a command for adding an event task to the task list
 * managed by the chatbot. When executed, this command parses user input to extract
 * the event task details and adds it to the task list, along with the specified
 * start and end timings of the event.
 */
public class AddEventCommand extends Command {
    private static final String FROM = "/from ";
    private static final String TO = "/to ";
    private static final String COMMAND = "event ";
    /**
     * Executes the command to add an event to the list of recorded tasks.
     *
     * @param tasks Recorded list of tasks.
     * @param ui Format of output shown.
     * @param storage To save and load tasks recorded.
     * @param message Input of user.
     * @throws LeluException If the input is invalid or with the wrong format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        checkEmptyDescription(message, COMMAND, LeluException.ErrorType.EVENT);
        Event t = checkFormat(message);
        assert message.length() >= (COMMAND + FROM + TO).length() : "Input not handled properly";
        return tasks.addTask(t);
    }

    private Event checkFormat(String message) throws LeluException {
        String[] t = message.replaceFirst(COMMAND, "").split(FROM);
        String details = t[0].trim();
        if (t.length < 2 || details.length() == 0) {
            InvalidFormatException.callInvalidFormatException(LeluException.ErrorType.EVENT);
        }
        String[] frTo = t[1].split(TO);
        String from = frTo[0].trim();
        String to = frTo[1].trim();
        if (frTo.length < 2 || from.length() == 0 || to.length() == 0) {
            InvalidFormatException.callInvalidFormatException(LeluException.ErrorType.EVENT);
        }
        return new Event(details, from, to);
    }
}
