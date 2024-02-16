package duke.action;

import duke.exception.WrongIndexException;
import duke.task.Task;

/**
 * Represents an action to mark a task as done in the task list.
 */
public class Mark implements Action {

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
     * Gets the response message indicating the marking of the tasks as done.
     *
     * @return A string representing the response message.
     */
    @Override
    public String response() {
        StringBuilder response = new StringBuilder("Nice! I've marked the following tasks as done:\n");
        for (int index : indices) {
            Task markedTask = tasks.get(index);
            response.append(markedTask.toString()).append("\n");
        }
        return response.toString();
    }
}
