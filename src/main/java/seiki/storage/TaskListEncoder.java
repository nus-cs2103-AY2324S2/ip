package seiki.storage;

import seiki.data.TaskList;

import java.util.ArrayList;

public class TaskListEncoder {

    public static ArrayList<String> encodeTaskList(TaskList taskList) {
        final ArrayList<String> encodedTasks = new ArrayList<>();
        taskList.getAllTasks().forEach(task -> encodedTasks.add(task.toFile()));
        return encodedTasks;
    }
}
