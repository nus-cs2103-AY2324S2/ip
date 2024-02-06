package commands;

import util.Ui;
import util.TaskList;
import util.Storage;
import exceptions.ChatBotException;

/**
 * The DeleteCommand class represents a command to delete a task from the task list.
 * It extends the Command class and implements the execute method to execute the command.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructs a DeleteCommand object with the given index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by deleting the task at the specified index from the task list,
     * and saving the updated task list to storage. Handles any exceptions that may occur during the process.
     *
     * @param taskList The TaskList containing the current tasks.
     * @param ui       The Ui instance for user interaction and output.
     * @param storage  The Storage instance for saving tasks or loading data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.deleteTask(this.index);
            storage.saveToFile(taskList);
        } catch (ChatBotException e) {
            System.out.println("\tAn error occurred when deleting");
        }
    }
}
