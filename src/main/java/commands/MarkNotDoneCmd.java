package commands;

import destiny.DestinyException;
import destiny.TaskList;

/**
 * Command that marks indicated task as not done.
 */
public class MarkNotDoneCmd extends Command{
    private String indexStr;

    /**
     * Constructor for the MarkNotDoneCmd class.
     *
     * @param indexStr The index which needs to be unmarked.
     */
    public MarkNotDoneCmd(String indexStr) {
        this.indexStr = indexStr;
    }

    /**
     * Marks the indicated task as not done.
     *
     * @param tasks The set of tasks saved by Destiny.
     * @return Message for successful unmark.
     * @throws DestinyException If 0 < index < tasks.size() or if integer not provided.
     */
    @Override
    public String execute(TaskList tasks) throws DestinyException {
        int index;

        try {
            index = Integer.parseInt(indexStr);
        } catch (NumberFormatException e) {
            throw new DestinyException(tasks.size() != 0
                    ? "Invalid input type\nEnter a number between 1 and " + tasks.size()
                    : "Invalid input type\nCan't unmark either cause the list is empty");
        }

        if (index < 1 || index > tasks.size()) {
            throw new DestinyException("Please enter a number between 1 and " + tasks.size());
        }

        tasks.get(index - 1).markAsUndone();
        return "OK, I've marked this task as not done yet:\n  " + tasks.get(index - 1).toString();
    }
}
