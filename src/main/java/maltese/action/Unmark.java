package maltese.action;

import maltese.exception.MalteseException;
import maltese.exception.NoIndexException;
import maltese.exception.WrongIndexException;
import maltese.task.Task;

/**
 * Represents an action to unmark a task as done.
 */

public class Unmark implements Action {
    private static final int UNMARK_START_INDEX = 7;
    private int[] indices;
    private TaskList tasks;
    /**
     * Constructs a Unmark action with the specified index and task list.
     *
     * @param index The index of the task to be unmarked.
     * @param tasks The task list containing the tasks.
     * @throws WrongIndexException If the index is invalid.
     */
    public Unmark(int index, TaskList tasks) throws WrongIndexException {
        this(new int[]{index}, tasks);
    }

    /**
     * Constructs a Unmark action with the specified indices and task list.
     *
     * @param indices The indices of the tasks to be marked as done.
     * @param tasks   The task list containing the tasks.
     * @throws WrongIndexException If any index is invalid.
     */
    public Unmark(int[] indices, TaskList tasks) throws WrongIndexException {
        for (int index : indices) {
            if (!tasks.validateIndex(index)) {
                throw new WrongIndexException();
            }
        }
        this.indices = indices;
        for (int index : indices) {
            tasks.unmarkTask(index);
        }
        this.tasks = tasks;
    }
    /**
     * Parses the command for the "unmark" action.
     *
     * @param command  The command string containing the indices of tasks to unmark.
     * @param taskList The TaskList containing tasks to be unmarked.
     * @return An Action object representing the "unmark" action with specified indices.
     * @throws MalteseException If there are no indices provided or if there are duplicate indices.
     */
    public static Unmark parse(String command, TaskList taskList) throws MalteseException {
        String[] words = command.split(" ");
        if (words.length > 1) {
            String[] indicesString = command.substring(UNMARK_START_INDEX).trim().split(" ");
            if (indicesString.length > 0) {
                int[] indices = Action.parseIndices(indicesString);
                Action.checkForDuplicateIndices(indices);
                return new Unmark(indices, taskList);
            }
        }
        throw new NoIndexException();
    }

    /**
     * Gets the response message indicating the successful unmarking of a task as not done.
     *
     * @return A string representing the response message.
     */
    @Override
    public String getResponse() {
        StringBuilder response = new StringBuilder("OK, I've marked this task as not done yet:\n");
        for (int index : indices) {
            Task markedTask = tasks.get(index);
            response.append(markedTask.toString()).append("\n");
        }
        return response.toString();
    }
}
