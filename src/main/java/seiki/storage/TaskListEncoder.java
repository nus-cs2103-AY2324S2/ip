package seiki.storage;

import java.util.ArrayList;

import seiki.data.TaskList;


public class TaskListEncoder {

    public static ArrayList<String> encodeTaskList(TaskList taskList) {
        final ArrayList<String> encodedTasks = new ArrayList<>();
        taskList.getAllTasks().forEach(task -> encodedTasks.add(task.toFile()));
        return encodedTasks;
    }
}
