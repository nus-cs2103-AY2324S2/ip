package command;

import exception.BuddyException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents Command that marks or unmarks tasks.
 */
public class MarkCommand extends Command {
    CommandType commandWord;
    int taskIndex;

    /**
     * Creates MarkCommand with specified task to mark or unmark.
     *
     * @param commandWord Either mark or unmark.
     * @param taskIndex Task to be modified.
     */
    public MarkCommand(CommandType commandWord, int taskIndex) {
        this.commandWord = commandWord;
        this.taskIndex = taskIndex;
    }

    /**
     * Marks or unmarks tasks.
     *
     * @param tasks TaskList consisting of Tasks.
     * @param ui Current Ui object used.
     * @param storage Current Storage object used.
     * @throws BuddyException If index of task not found in TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        try {
            Task task = tasks.getTask(taskIndex);
            task.setDone(commandWord.equals(CommandType.MARK));
            ui.showMark(task);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new BuddyException("Not a valid task number!");
        }
    }
}
