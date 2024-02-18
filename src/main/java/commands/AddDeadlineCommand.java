package commands;

import exceptions.InvalidFormatException;
import exceptions.LeluException;
import tasks.Deadline;
import tasks.Task;
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
        if (message.trim().equals("deadline")) {
            InvalidFormatException.callInvalidFormatException(LeluException.ErrorType.DEADLINE);
        }
        String[] s = message.replaceFirst("deadline ", "").split("/by ");
        if (s.length < 2) {
            InvalidFormatException.callInvalidFormatException(LeluException.ErrorType.DEADLINE);
        }
        Task t = new Deadline(s[0].replaceAll("\\s+$", ""), s[1]);
        assert message.length() >= "deadline /by yyyy-MM-dd HH:mm".length() : "Input not handled properly";
        return tasks.addTask(t);
    }
}
