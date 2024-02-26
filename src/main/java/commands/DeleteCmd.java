package commands;

import destiny.DestinyException;
import destiny.TaskList;

/**
 * Command that deletes specified task in tasklist.
 */
public class DeleteCmd extends Command {
    private String indexStr;

    /**
     * Constructor for the DeleteCmd class.
     *
     * @param indexStr The index of the task to be deleted.
     */
    public DeleteCmd(String indexStr) {
        this.indexStr = indexStr;
    }
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

        return tasks.delete(index);
    }
}
