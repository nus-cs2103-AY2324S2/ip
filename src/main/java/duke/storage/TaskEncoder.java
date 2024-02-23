package duke.storage;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Encodes the object into a data file for storage.
 */
public class TaskEncoder {
    /**
     * Encodes the tasks to strings for saving.
     *
     * @param tasks every task in the task list
     * @return the encoded tasks
     */
    public static ArrayList<String> encodeTask(ArrayList<Task> tasks) {
        ArrayList<String> encodedTask = new ArrayList<>();
        for (Task task : tasks) {
            encodedTask.add(task.toString());
        }
        return encodedTask;
    }
}
