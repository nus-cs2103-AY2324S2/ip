package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

public class DeleteCommand implements Command {
    int index;

    /**
     * Initializes a new DeleteCommand to delete a task based on the index in the TaskList.
     *
     * @param index the index of task in the TaskList that wants to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Delete the task in the TaskList and display the UI message.
     *
     * @param tasks the TaskList for which the task is in
     * @param ui the UI that will be used to display the message
     * @return a String of the UI message and the deleted task
     * @throws DukeException if index is greater than the amount of Task in the TaskList
     */
    public String execute(TaskList tasks, Ui ui) throws DukeException {
        assert index > 0;
        if (index + 1 > tasks.size()) {
            throw new DukeException(Ui.indexTooBigError(tasks));
        }
        Task task = tasks.get(index);
        tasks.remove(index);
        return ui.deleteMessage(task.toString(), tasks.size());
    }

    public boolean isExit() {
        return false;
    }
}
