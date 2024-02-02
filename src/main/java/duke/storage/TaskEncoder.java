package duke.storage;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Encodes the object into a data file for storage.
 */
public class TaskEncoder {
    public static ArrayList<String> encodeTask(ArrayList<Task> tasks) {
        ArrayList<String> encodedTask = new ArrayList<>();
        for (Task task : tasks) {
            encodedTask.add(task.toString());
        }
        return encodedTask;
    }
}
