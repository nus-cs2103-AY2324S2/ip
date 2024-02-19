package command;

import exceptions.DuplicateInsertionException;
import storage.Storage;
import task.Task;
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
     * @return A string containing the result message for this operation.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            tasks.addTask(this.task);
            storage.writeLine(this.saveData);
            UI.print("Gotcha. I've added this task:");
            UI.print("\t" + this.task);
            UI.print(String.format("Now you have %d tasks in the list.", tasks.getSize()));

            String result = "Gotcha. I've added this task:\n";
            result += "\t" + this.task + "\n";
            result += String.format("Now you have %d tasks in the list.\n", tasks.getSize());
            return result;
        } catch (DuplicateInsertionException e) {
            UI.print("Naughty boy... You have already added this task into the task list");
            return "Naughty boy... You have already added this task into the task list\n";
        }
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
