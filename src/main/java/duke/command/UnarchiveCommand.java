package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnarchiveCommand extends Command {

    private int index;

    public UnarchiveCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command based on the given parameters.
     *
     * @param tasks         The TaskList that holds the list of tasks.
     * @param archiveTasks  The list of archived tasks.
     * @param ui            The Ui to interact with the user.
     * @param storage       The Storage to save tasks to a file.
     * @param archived      The storage to save the archived tasks to a file.
     * @throws DukeException If there is an error while executing the command.
     */
    @Override
    public String execute(TaskList tasks, TaskList archiveTasks, Ui ui,
                          Storage storage, Storage archived) throws DukeException {
        if (this.index <= tasks.getTaskSize() && this.index > 0) {
            Task taskToUnarchive = archiveTasks.getTasks().get(index - 1);
            tasks.add(taskToUnarchive);
            String s = ui.showUnarchiveMsg(archiveTasks.getTasks().get(this.index - 1),
                    archiveTasks.getTaskSize());
            archiveTasks.delete(this.index);
            storage.saveTask(tasks);
            archived.saveTask(archiveTasks);
            return s;
        } else {
            throw new DukeException("Invalid index. \n"
                    + "Please provide a valid index within the range 1 to "
                    + archiveTasks.getTaskSize() + "." + "\n");
        }
    }
}
