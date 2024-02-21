package TaskFlow.command;

import TaskFlow.exception.DukeException;
import TaskFlow.storage.Storage;
import TaskFlow.task.Task;
import TaskFlow.task.TaskList;
import TaskFlow.ui.Ui;

public class ArchiveCommand extends Command {
    private int index;

    public ArchiveCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command based on the given parameters.
     *
     * @param tasks         The list of tasks.
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
            Task taskToArchive = tasks.getTasks().get(index - 1);
            archiveTasks.add(taskToArchive);
            String s = ui.showArchiveMsg(tasks.getTasks().get(this.index - 1),
                    archiveTasks.getTaskSize());
            tasks.delete(this.index);
            storage.saveTask(tasks);
            archived.saveTask(archiveTasks);
            return s;
        } else {
            throw new DukeException("Invalid index. \n"
                    + "Please provide a valid index within the range 1 to "
                    + tasks.getTaskSize() + "." + "\n");
        }
    }
}
