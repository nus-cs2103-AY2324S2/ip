package toothless.commands;

import toothless.Storage;
import toothless.TaskList;
import toothless.ToothlessException;
import toothless.Ui;
import toothless.tasks.Task;
import toothless.tasks.Deadline;

/**
 * Represents a command to add a deadline task to the task list.
 * The detail provided includes the task description and its deadline.
 */
public class DeadlineCommand extends Command{
    private String detail;

    /**
     * Constructs a DeadlineCommand with the specified detail, which includes the task description
     * and its deadline.
     * @param detail The task detail, expected to contain a description and a deadline.
     */
    public DeadlineCommand(String detail) {
        this.detail = detail;
    }

    /**
     * Executes the command to add a new deadline task to the task list. Parses the task detail to separate
     * the description from the deadline, creates a new deadline task, adds it to the task list, and displays
     * a message to the user about the addition.
     * @param ui The user interface to interact with.
     * @param taskList The task list to be manipulated or queried.
     * @param storage The storage system for loading or saving tasks.
     * @return String message to be displayed to the user.
     * @throws ToothlessException If the task detail is invalid, either missing a description or a deadline.
     */
    @Override
    public String handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException {
        if (detail.equals("")) {
            throw new ToothlessException(ui.showNoTaskNameWarning());
        }

        int dateIndex = detail.indexOf("/by");
        if (dateIndex == -1) {
            throw new ToothlessException(ui.showNoDeadlineWarning());
        }

        String description = detail.substring(0, dateIndex - 1);
        String date = detail.substring(dateIndex + 4);

        Task t = new Deadline(description, date);
        taskList.addTask(t);

        return ui.showAddedTask(t, taskList.size());
    }

    /**
     * Indicates whether the command is an exit command.
     * @return False, as the command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
