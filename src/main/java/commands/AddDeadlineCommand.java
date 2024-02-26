package commands;

import exceptions.InvalidFormatException;
import exceptions.LeluException;
import tasks.Deadline;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * This class represents a command for adding a deadline task to the task list
 * managed by the chatbot. When executed, this command parses user input to extract
 * the deadline task details and adds it to the task list, along with the specified
 * due date and time.
 */
 public class AddDeadlineCommand extends Command {
     private static final String BY = "/by ";
     private static final String COMMAND = "deadline ";

    /**
     * Executes the command to add a deadline to the list of recorded tasks.
     *
     * @param tasks Recorded list of tasks.
     * @param ui Format of output shown.
     * @param storage To save and load tasks recorded.
     * @param message Input of user.
     * @throws LeluException If the input is invalid or with the wrong format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        checkEmptyDescription(message, COMMAND, LeluException.ErrorType.DEADLINE);
        Deadline t = checkFormat(message);
        assert message.length() >= (COMMAND + BY + Deadline.DATE_FORMAT).length() : "Input not handled properly";
        return tasks.addTask(t);
    }

    private Deadline checkFormat(String message) throws LeluException {
        String[] s = message.replaceFirst(COMMAND, "").split(BY);
        String details = s[0].trim();
        if (s.length < 2 || details.length() == 0) {
            InvalidFormatException.callInvalidFormatException(LeluException.ErrorType.DEADLINE);
        }
        return new Deadline(details, s[1]);
    }
}
