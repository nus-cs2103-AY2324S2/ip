package ciara.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import ciara.exceptions.StorageException;
import ciara.exceptions.TaskCorruptedException;
import ciara.exceptions.TaskNotSupportedException;
import ciara.storage.Task.TaskType;

/**
 * The Storage class handles storing of application data to system
 *
 * @author Ryan NgWH
 */
public class Storage {
    /**
     * Writes JSON array to file in JSON format
     *
     * @param array JSON array to be written to file
     * @param file  Path of the file to write to
     */
    public static void saveToFile(JSONArray array, File file) throws IOException {
        // Create file (if required)
        file.createNewFile();

        assert file.canWrite(); // Ensure that file is writable

        // Write to file
        FileWriter writer = new FileWriter(file);
        array.write(writer, 2, 0);
        writer.close();
    }

    /**
     * Populates storage array from file in JSON format
     *
     * @param taskList TaskList to populate
     * @param file     File to load values from
     */
    public static void loadFromFile(TaskList taskList, File file)
            throws TaskNotSupportedException, FileNotFoundException, TaskCorruptedException, StorageException {
        // Check if file is readable
        if (!file.canRead()) {
            throw new FileNotFoundException("File cannot be read");
        }

        try {
            // Read stored tasks from file
            FileInputStream input = new FileInputStream(file);
            JSONArray jsonArray = new JSONArray(new JSONTokener(input));

            // Populate storage array
            for (int i = 0; i < jsonArray.length(); i++) {
                // Get json entry
                JSONObject entry = jsonArray.getJSONObject(i);
                assert entry != null; // Entry should exist

                // Parse JSON entry to task
                Task task;
                switch (TaskType.valueOf(entry.getString("type"))) {
                case TODO:
                    task = new Todo(entry.getString("description"),
                            entry.getBoolean("isCompleted"),
                            entry.getBoolean("isArchived"));
                    break;

                case DEADLINE:
                    task = new Deadline(entry.getString("description"),
                            entry.getLong("dueDate"),
                            entry.getBoolean("isCompleted"),
                            entry.getBoolean("isArchived"));
                    break;

                case EVENT:
                    task = new Event(entry.getString("description"),
                            entry.getLong("startDate"),
                            entry.getLong("endDate"),
                            entry.getBoolean("isCompleted"),
                            entry.getBoolean("isArchived"));
                    break;

                default:
                    throw new TaskNotSupportedException(
                            String.format("Task '%s' not currently supported", entry.getString("type")));
                }

                assert task != null; // Task should exist

                // Add task to storage array
                taskList.addTask(task, task.getIsArchived());
            }
        } catch (IllegalArgumentException | JSONException e) {
            throw new TaskCorruptedException("Task corrupted, will not be loaded");
        }
    }
}
