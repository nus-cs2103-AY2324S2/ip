package maltese.action;

import maltese.exception.MalteseException;
import maltese.exception.NoIndexException;
import maltese.exception.WrongIndexException;
import maltese.task.Task;

/**
 * Represents an action to mark a task as done in the task list.
 */
public class Mark implements Action {
    private static final int MARK_START_INDEX = 5;

    private int[] indices;
    private TaskList tasks;

    public Mark(int index, TaskList tasks) throws WrongIndexException {
        this(new int[]{index}, tasks);
    }

    /**
     * Constructs a Mark action with the specified indices and task list.
     *
     * @param indices The indices of the tasks to be marked as done.
     * @param tasks   The task list containing the tasks.
     * @throws WrongIndexException If any index is invalid.
     */
    public Mark(int[] indices, TaskList tasks) throws WrongIndexException {
        for (int index : indices) {
            if (!tasks.validateIndex(index)) {
                throw new WrongIndexException();
            }
        }
        this.indices = indices;
        for (int index : indices) {
            tasks.markTask(index);
        }
        this.tasks = tasks;
    }

    /**
     * Parses the mark command and creates a Mark action.
     *
     * @param command  The mark command to be parsed.
     * @param taskList The TaskList containing the tasks.
     * @return A Mark action representing the tasks marked as done.
     * @throws MalteseException If there is an error parsing the command or marking the tasks.
     */

    public static Mark parse(String command, TaskList taskList) throws MalteseException {
        String[] words = command.split(" ");
        if (words.length > 1) {
            String[] indicesString = command.substring(MARK_START_INDEX).trim().split(" ");
            if (indicesString.length > 0) {
                int[] indices = Action.parseIndices(indicesString);
                Action.checkForDuplicateIndices(indices);
                return new Mark(indices, taskList);
            }
        }
        throw new NoIndexException();
    }


    /**
     * Gets the response message indicating the marking of the tasks as done.
     *
     * @return A string representing the response message.
     */
    @Override
    public String getResponse() {
        StringBuilder response = new StringBuilder("Nice! I've marked the following tasks as done:\n");
        for (int index : indices) {
            Task markedTask = tasks.get(index);
            response.append(markedTask.toString()).append("\n");
        }
        return response.toString();
    }
}
