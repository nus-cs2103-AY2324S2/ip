package duke.commands;

import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command subclass for adding tasks
 */
public class AddCommand extends Command {
    private final Task newTask; /** Task to add */
    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.addTask(newTask);
            storage.saveTasks(tasks);
            return ui.addTask(newTask, tasks.getNumTasks());
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
