package seiki.storage;

import java.util.ArrayList;

import seiki.data.TaskList;

/**
 * Encodes the {@code TaskList} object into a data file for storage.
 */
public class TaskListEncoder {

    /**
     * Encodes all the {@code Task} in the {@code taskList} into a list of decodable and
     * readable string presentation for storage.
     * @param taskList
     * @return
     */
    public static ArrayList<String> encodeTaskList(TaskList taskList) {
        final ArrayList<String> encodedTasks = new ArrayList<>();
        taskList.getAllTasks().forEach(task -> encodedTasks.add(task.toFile()));
        return encodedTasks;
    }
}
