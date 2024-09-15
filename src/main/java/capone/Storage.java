package capone;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import capone.exceptions.TaskListCorruptedException;
import capone.tasks.Deadline;
import capone.tasks.Event;
import capone.tasks.Task;
import capone.tasks.ToDo;

/**
 * The Storage class is responsible for reading and writing tasks
 * to a JSON file. It provides methods to initialize the file if
 * it doesn't exist, read and writes tasks to the file.
 * The file is stored in a specified folder with a given filename.
 *
 * @author Tay Rui-Jie
 */
public class Storage {
    protected static final String STORAGE_PATH = "./data/";
    protected static final String STORAGE_FILE = "tasks.json";
    private final String jsonFilename;
    private final String jsonFilepath;

    /**
     * Constructor for the Storage class.
     *
     * @param path The path where the JSON file is stored.
     * @param name The name of the JSON file.
     */
    public Storage(String path, String name) {
        this.jsonFilepath = path;
        this.jsonFilename = name;
    }

    /**
     * Gets the full path of the JSON file,
     * including the folder path and filename.
     *
     * @return The full path of the JSON file.
     */
    private String getFullPath() {
        return this.jsonFilepath + this.jsonFilename;
    }

    /**
     * Gets the filename of the JSON file.
     *
     * @return The filename of the JSON file.
     */
    private String getFileName() {
        return this.jsonFilename;
    }

    /**
     * Gets the folder path where the JSON file is stored.
     *
     * @return The folder path where the JSON file is stored.
     */
    private String getFilePath() {
        return this.jsonFilepath;
    }

    /**
     * Writes tasks from the task list to the JSON file.
     *
     * @param taskList The TaskList containing tasks to be written to the file.
     */
    public void writeTasksToJsonFile(TaskList taskList) {
        JSONArray jsonArray = new JSONArray();

        for (Task task : taskList) {
            JSONObject jsonTask = new JSONObject();
            jsonTask.put("taskType", task.getTaskType());
            jsonTask.put("description", task.getDescription());
            jsonTask.put("status", task.getStatus());

            // Add type-specific information for Deadline and Event tasks
            if (task instanceof Deadline) {
                jsonTask.put("deadline", ((Deadline) task).getDeadline());
            } else if (task instanceof Event) {
                jsonTask.put("fromDate", ((Event) task).getFromDateTime());
                jsonTask.put("toDate", ((Event) task).getToDateTime());
            }

            jsonArray.put(jsonTask);
        }

        try (FileWriter fileWriter = new FileWriter(this.getFullPath())) {
            jsonArray.write(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads tasks from the JSON file and adds them to the task list.
     *
     * @param taskList The TaskList where tasks will be added.
     * @throws TaskListCorruptedException If the JSON file is corrupted or has an invalid format.
     */
    public void readTasksFromJsonFile(TaskList taskList) throws TaskListCorruptedException {
        try {
            this.initFile();

            String jsonContent = new String(Files.readAllBytes(Paths.get(this.getFullPath())));
            JSONArray jsonArray = new JSONArray(jsonContent);

            this.initTaskObjects(taskList, jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            this.initFile();
            throw new TaskListCorruptedException("Detected corrupted task list file."
                    + " New task list file will be created when new tasks are created.");
        }
    }

    /**
     * Converts JSON objects to Tasks and adds them to the task list.
     *
     * @param taskList The TaskList where tasks will be added.
     * @param jsonArray The JSON array containing task details in JSON format.
     * @throws TaskListCorruptedException If the JSON object(s) contain an invalid format.
     */
    private void initTaskObjects(TaskList taskList, JSONArray jsonArray) throws TaskListCorruptedException {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonTask = jsonArray.getJSONObject(i);

                String type = jsonTask.getString("taskType");
                String description = jsonTask.getString("description");
                boolean status = jsonTask.getBoolean("status");

                if (type.equals("todo")) {
                    taskList.addTask(new ToDo(description, status));
                } else if (type.equals("deadline")) {
                    String deadline = jsonTask.getString("deadline");
                    taskList.addTask(new Deadline(description, status, deadline));
                } else if (type.equals("event")) {
                    String fromDate = jsonTask.getString("fromDate");
                    String toDate = jsonTask.getString("toDate");
                    taskList.addTask(new Event(description, status, fromDate, toDate));
                }
            }
        } catch (JSONException e) {
            this.initFile();
            throw new TaskListCorruptedException("Detected corrupted JSON object."
                    + " New task list file will be created when new tasks are created.");
        }
    }

    /**
     * Initializes the JSON file if it does not exist.
     */
    public void initFile() {
        // Create the folder if it doesn't exist
        File folder = new File(this.getFilePath());
        if (!folder.exists()) {
            folder.mkdir();
        }

        // Create the file within the folder
        File file = new File(folder, this.getFileName());

        if (!file.exists()) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write("[]");
                assert (new String(Files.readAllBytes(Paths.get(this.getFullPath()))).equals("[]"))
                        : "New task file created is not initialised properly (i.e. does not contain '[]'`)";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
