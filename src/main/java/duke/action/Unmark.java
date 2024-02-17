package duke.action;

import duke.exception.DukeException;
import duke.exception.NoIndexException;
import duke.exception.WrongIndexException;
import duke.task.Task;

/**
 * Represents an action to unmark a task as done.
 */

public class Unmark implements Action {
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

    public static Unmark parse(String command, TaskList taskList) throws DukeException {
        String[] words = command.split(" ");
        if (words.length > 1) {
            String[] indicesString = command.substring(7).trim().split(" "); // Remove
            // "mark" and split by spaces
            if (indicesString.length > 0) {
                int[] indices = new int[indicesString.length];
                for (int i = 0; i < indicesString.length; i++) {
                    indices[i] = Integer.parseInt(indicesString[i]) - 1;
                }
                return new Unmark(indices, taskList);
            } else {
                throw new NoIndexException();
            }
        } else {
            throw new NoIndexException();
        }

    }

    /**
     * Gets the response message indicating the successful unmarking of a task as not done.
     *
     * @return A string representing the response message.
     */
    @Override
    public String response() {
        StringBuilder response = new StringBuilder("OK, I've marked this task as not done yet:\n");
        for (int index : indices) {
            Task markedTask = tasks.get(index);
            response.append(markedTask.toString()).append("\n");
        }
        return response.toString();
    }
}
