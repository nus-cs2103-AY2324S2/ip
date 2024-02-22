package haro.command;

import haro.Storage;
import haro.TaskList;
import haro.Ui;
import haro.task.Task;

/**
 * The AddCommand class represents a command to add a task to the TaskList.
 * It extends the base Command class and implements the execute method to add a task.
 */

public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand Instance with the Task object to be added.
     * @param task Task object to be added to the TaskList
     */
    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    /**
     * Executes the command by adding the task to the TaskList and displaying a corresponding message.
     *
     * @param taskList TaskList to be modified by the command
     * @param ui       Ui for displaying messages related to command execution
     * @param storage  Storage for saving and loading task data
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.task);
        return ui.printAddTask(this.task, taskList.getSize());
    }
}
