package duke.command;

import duke.task.Task;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import java.io.IOException;

/**
 * Class that represents a command that adds a {@link Task} in a {@link TaskList}.
 */
public class AddTaskCommand extends Command {
    /**  Task object to be added into the TaskList */
    private Task taskToBeAdded;

    /**
     * Constructs a AddTaskCommand Object.
     *
     * @param task Task Object to be added into the TaskList.
     */
    public AddTaskCommand(Task task) {
        this.taskToBeAdded = task;
    }

    /**
     * Runs the AddTaskCommand to add a task into the existing TaskList.
     *
     * @param taskList Existing TaskList to be updated.
     * @param ui UserInterface Object.
     * @param storage Existing Storage to be updated.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(taskToBeAdded);
        try {
            storage.saveStorage(taskList.getTaskStore());
        } catch (IOException e) {
            return ui.showError(e.getMessage());
        }
        return ui.showAddedTask(taskToBeAdded, taskList.listSize());
    }

    /**
     * Returns a boolean value telling us whether this command is an exit command.
     *
     * @return Boolean value indicating whether this command is an exist command.
     */
    public boolean isExit() {
        return false;
    }
}
