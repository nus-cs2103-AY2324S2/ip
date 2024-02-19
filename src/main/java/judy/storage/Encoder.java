package judy.storage;

import java.util.ArrayList;

import judy.task.Task;

/**
 * The Encoder class is to encode a list of Task objects into encoded string format for storage.
 */
public class Encoder {

    /**
     * Encodes the list of Task objects into a decodable and readable string representation.
     */
    public static String encodeTasks(ArrayList<Task> tasks) {
        StringBuilder encodedTasks = new StringBuilder();
        for (Task t : tasks) {
            encodedTasks.append(t.encode()).append("\n");
        }
        return encodedTasks.toString();
    }
}
