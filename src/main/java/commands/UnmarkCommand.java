package commands;

import util.Ui;
import util.TaskList;
import util.Storage;
import exceptions.ChatBotException;

/**
 * The UnmarkCommand class represents a command to mark a task as not done in the task list.
 * It extends the Command class and implements the execute method to perform the unmarking operation.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified index of the task to be unmarked.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark a task as not done in the task list by calling the unmarkTask method
     * in the TaskList, then saving the updated task list to the storage.
     *
     * @param taskList The TaskList containing the current tasks.
     * @param ui       The Ui instance for user interaction and output.
     * @param storage  The Storage instance for saving tasks or loading data.
     */
    @Override
    public UserCommand execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.unmarkTask(this.index);
            storage.saveToFile(taskList);
            return new UserCommand("\tI have unmarked this task", "\t" + taskList.getTaskIndex(this.index - 1));
        } catch (ChatBotException e) {
            System.out.println("\tAn error occurred when unmarking");
            return new UserCommand("\tAn error occurred when unmarking");
        }
    }
}
