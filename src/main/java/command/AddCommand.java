package command;

import task.Task;
import task.TaskList;
import utilities.Storage;
import utilities.Ui;

/**
 * Controls what happens when a task is added.
 */
public class AddCommand extends Command {
    /**
     * Task to be added into the task list.
     */
    private Task taskToAdd;

    /**
     * AddCommand class constructor.
     * @param newTask New task created and waiting to be added into the task list.
     */
    public AddCommand(Task newTask) {
        super(false);
        this.taskToAdd = newTask;
    }

    /**
     * Executes the add task process.
     * @param taskList The task list that the task is added to.
     * @param storage The storage that the task list is stored in after the task is added.
     * @param ui Provides corresponding user output based on whether the process is successful or not.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.addTask(this.taskToAdd);
        storage.save(taskList);
        ui.showTaskListLength(taskList);
    }
}
