package detective.storage;

import detective.task.Task;

import java.util.ArrayList;

public class TaskEncoder {
    public static ArrayList<String> encodeTask(ArrayList<Task> tasks) {
        ArrayList<String> encodedTask = new ArrayList<>();
        for (Task task : tasks) {
            encodedTask.add(task.toString());
        }
        return encodedTask;
    }
}
