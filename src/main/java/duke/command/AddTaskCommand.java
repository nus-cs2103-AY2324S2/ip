package duke.command;

import duke.task.Task;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import java.io.IOException;

public class AddTaskCommand extends Command {

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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(taskToBeAdded);
        ui.showAddedTask(taskToBeAdded, taskList.listSize());
        try {
            storage.saveStorage(taskList.getTaskStore());
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
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
