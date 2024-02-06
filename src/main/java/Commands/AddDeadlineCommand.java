package Commands;

import Exceptions.LeluException;
import Tasks.Deadline;
import Tasks.Task;
import TasksStorage.Storage;
import TasksStorage.TaskList;
import Ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        Task t = Deadline.DeadlineOf(message);
        tasks.addTask(t);
    }
}
