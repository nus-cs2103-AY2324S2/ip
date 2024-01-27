package command;

import task.Task;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

/**
 * The AddCommand class represents a command to add a new task to the task list.
 * It extends the Command class and implements the execute method to carry out the addition of the task.
 */
public class AddCommand extends Command {

    /**
     * The task to be added.
     */
    private Task task;

    /**
     * The string representation of the data to be saved in storage.
     */
    private String saveData;

    /**
     * Constructs an AddCommand with the specified task and saveData.
     *
     * @param task     The task to be added.
     * @param saveData The string representation of the data to be saved in storage.
     */
    public AddCommand(Task task, String saveData) {
        this.task = task;
        this.saveData = saveData;
    }

    /**
     * Executes the AddCommand, adding the task to the task list, saving data in storage,
     * and printing a confirmation message to the user.
     *
     * @param tasks   The TaskList to which the task is added.
     * @param storage The Storage to save the task data.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.addTask(this.task);
        storage.writeLine(this.saveData);
        UI.print("Got it. I've added this task:");
        UI.print("\t" + this.task);
        UI.print(String.format("Now you have %d tasks in the list.", tasks.getSize()));
    }

    /**
     * Retrieves the string representation of the data to be saved in storage.
     *
     * @return The saveData string.
     */
    @Override
    public String getTestData() {
        return saveData;
    }
}