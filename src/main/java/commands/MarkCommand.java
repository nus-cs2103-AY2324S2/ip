package commands;

import util.Ui;
import util.TaskList;
import util.Storage;
import exceptions.ChatBotException;

/**
 * The MarkCommand class represents a command to mark a task as done in the task list.
 * It extends the Command class and implements the execute method to perform the marking operation.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with the specified task index to mark as done.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark the task at the specified index as done by calling the markTask method of TaskList.
     * If an error occurs during the marking process, it catches the ChatBotException and prints an error message.
     *
     * @param taskList The TaskList containing the current tasks.
     * @param ui       The Ui instance for user interaction and output.
     * @param storage  The Storage instance for saving tasks or loading data.
     */
    @Override
    public UserCommand execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.markTask(this.index);
            storage.saveToFile(taskList);
            return new UserCommand("\tI have marked this task as done", "\t" + taskList.getTaskIndex(this.index - 1));
        } catch (ChatBotException e) {
            System.out.println("\t Oops! An error occurred when marking tasks");
            return new UserCommand("\t Oops! An error occurred when marking tasks.");
        }
    }
}
