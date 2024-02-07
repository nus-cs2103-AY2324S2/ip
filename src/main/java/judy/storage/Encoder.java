package judy.storage;

import judy.task.Task;

import java.util.ArrayList;

public class Encoder {
    public static String encodeTasks(ArrayList<Task> tasks) {
        StringBuilder encodedTasks = new StringBuilder();
        for (Task t : tasks) {
            encodedTasks.append(t.encode()).append("\n");
        }
        return encodedTasks.toString();
    }
}
