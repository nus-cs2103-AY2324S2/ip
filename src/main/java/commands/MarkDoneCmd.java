package commands;

import destiny.DestinyException;
import destiny.TaskList;

/**
 * Command that marks indicated task as done.
 */
public class MarkDoneCmd extends Command {
    private String indexStr;

    /**
     * Constructor for the MarkDoneCmd class.
     *
     * @param indexStr The index which needs to be marked.
     */
    public MarkDoneCmd(String indexStr) {
        this.indexStr = indexStr;
    }

    /**
     * Marks the indicated task as done.
     *
     * @param tasks The set of tasks saved by Destiny.
     * @return Message for successful mark.
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
                    : "Invalid input type\nCan't mark either cause the list is empty");
        }

        if (index < 1 || index > tasks.size()) {
            throw new DestinyException("Please enter a number between 1 and " + tasks.size());
        }

        tasks.get(index - 1).markAsDone();
        return "Nice! I've marked this task as done:\n  " + tasks.get(index - 1).toString();
    }
}
