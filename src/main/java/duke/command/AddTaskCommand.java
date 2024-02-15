package duke.command;

import duke.JamieException;
import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

/**
 * Represents a command to add a task to the task list.
 */
public class AddTaskCommand extends Command {
    private final Task taskToAdd;

    /**
     * Constructs an AddTaskCommand with the specified task to add.
     *
     * @param taskToAdd The task to add.
     */
    public AddTaskCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    /**
     * Executes the command by adding the task to the task list, saving it to storage,
     * and displaying a message to indicate the task has been added.
     *
     * @param tasks   The task list.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving the task list.
     * @return
     * @throws IOException If there is an error while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamieException {
        tasks.addTask(taskToAdd);
        storage.save(tasks);
        return ui.showAddTaskMessage(taskToAdd, tasks);
    }
}
