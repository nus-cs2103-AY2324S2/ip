package command;

import exception.BuddyException;
import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * Represents Command that marks or unmarks tasks.
 */
public class MarkCommand extends Command {
    private CommandType commandWord;
    private int taskIndex;

    /**
     * Creates MarkCommand with specified task to mark or unmark.
     *
     * @param commandWord Either mark or unmark.
     * @param taskIndex Task to be modified.
     */
    public MarkCommand(CommandType commandWord, int taskIndex) {
        assert commandWord != null;
        assert taskIndex > 0;
        this.commandWord = commandWord;
        this.taskIndex = taskIndex;
    }

    /**
     * Marks or unmarks tasks.
     *
     * @param tasks TaskList consisting of Tasks.
     * @param storage Current Storage object used.
     * @throws BuddyException If index of task not found in TaskList.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws BuddyException {
        String response = "I've updated the following task:\n";
        try {
            Task task = tasks.getTask(taskIndex);
            task.setDone(commandWord.equals(CommandType.MARK));
            response += task;
            return response;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new BuddyException("Not a valid task number!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
