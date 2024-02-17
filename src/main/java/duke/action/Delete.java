package duke.action;

import duke.exception.DukeException;
import duke.exception.MissingIndexException;
import duke.exception.NoIndexException;
import duke.exception.WrongIndexException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents an action to delete tasks.
 */
public class Delete implements Action {
    private int[] indices;
    private List<Task> deletedTasks;
    private TaskList tasks;

    /**
     * Constructs a Delete action with the specified indices and task list.
     *
     * @param indices The indices of the tasks to be deleted.
     * @param tasks   The task list from which the tasks will be deleted.
     * @throws WrongIndexException If any index is invalid.
     */
    public Delete(int[] indices, TaskList tasks) throws WrongIndexException {
        this.indices = indices;
        this.deletedTasks = new ArrayList<>();
        for (int index : indices) {
            if (!tasks.validateIndex(index)) {
                throw new WrongIndexException();
            }
            this.deletedTasks.add(tasks.get(index)); // Retrieve the tasks before deletion
        }
        this.tasks = tasks;
        Arrays.sort(indices);
        deleteTasks();
    }

    public static Delete parse(String command, TaskList taskList) throws DukeException {
        String[] words = command.split(" ");
        if (words.length > 1) {
            String[] indicesString = command.substring(7).trim().split(" "); // Remove
            // "mark" and split by spaces
            if (indicesString.length > 0) {
                int[] indices = new int[indicesString.length];
                for (int i = 0; i < indicesString.length; i++) {
                    indices[i] = Integer.parseInt(indicesString[i]) - 1;
                }
                return new Delete(indices, taskList);
            } else {
                throw new NoIndexException();
            }
        } else {
            throw new MissingIndexException();
        }
    }

    /**
     * Deletes the tasks specified by the indices.
     */
    private void deleteTasks() {
        for (int i = indices.length - 1; i >= 0; i--) {
            tasks.deleteTask(indices[i]);
        }
    }

    /**
     * Gets the response message indicating the successful deletion of tasks.
     *
     * @return A string representing the response message.
     */
    @Override
    public String response() {
        StringBuilder response = new StringBuilder("Noted. I've removed the following tasks:\n");
        for (Task deletedTask : deletedTasks) {
            response.append(deletedTask.toString()).append("\n");
        }
        response.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
        return response.toString();
    }
}



