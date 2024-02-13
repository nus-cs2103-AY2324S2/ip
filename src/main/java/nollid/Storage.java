package nollid;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import nollid.exceptions.NollidException;
import nollid.tasks.Deadline;
import nollid.tasks.Event;
import nollid.tasks.Task;
import nollid.tasks.Todo;

/**
 * Storage class handles loading and updating tasks from/to a file.
 */
public class Storage {
    public static final Path DEFAULT_FILEPATH = Paths.get(".", "data", "nollid.json");
    public static final Path TEST_FILEPATH = Paths.get(".", "data", "tests", "test_valid.json");
    private final Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a List of Tasks from the nollid.Storage's file path.
     */
    public ArrayList<Task> load() {
        // If JSON file is unable to be read, return an empty list.
        JSONArray jsonArray;
        try {
            jsonArray = loadJsonArray();
        } catch (IOException | JSONException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }

        ArrayList<Task> taskList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            // If for any reason, a JSON object is unable to be read in the file, just go to the next object.
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Task taskToAdd = getTaskFromJsonObject(jsonObject);
                taskList.add(taskToAdd);
            } catch (JSONException | NollidException e) {
                System.out.println(e.getMessage());
            }
        }
        return taskList;
    }

    /**
     * Updates the storage on disk based on the current state of the given task list.
     */
    public void update(TaskList taskList) {
        JSONArray jsonArray = new JSONArray();

        if (taskList.isEmpty()) {
            try {
                Files.write(this.filePath, "".getBytes());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        for (Task t : taskList) {
            JSONObject jsonObject = getJsonObjectFromTask(t);
            jsonArray.put(jsonObject);
        }

        try {
            FileWriter writer = new FileWriter(filePath.toFile());
            jsonArray.write(writer, 4, 0);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a Task object based on the information provided in the JSONObject.
     *
     * @param jsonObject The JSONObject containing task information.
     * @return The Task object created from the JSONObject.
     * @throws NollidException If there is an issue creating the Task object.
     */
    private Task getTaskFromJsonObject(JSONObject jsonObject) throws NollidException {
        String taskType = jsonObject.getString("type");
        String description = jsonObject.getString("description");
        ArrayList<String> tags = getTagsFromJsonObject(jsonObject);

        Task taskToAdd = null;
        switch (taskType) {
        case "todo":
            taskToAdd = new Todo(description, tags);
            break;
        case "deadline":
            LocalDateTime deadline = Parser.getLocalDateTimeFromString(jsonObject.getString("deadline"));
            taskToAdd = new Deadline(description, deadline, tags);
            break;
        case "event":
            LocalDateTime from = Parser.getLocalDateTimeFromString(jsonObject.getString("from"));
            LocalDateTime to = Parser.getLocalDateTimeFromString(jsonObject.getString("to"));
            try {
                taskToAdd = new Event(description, from, to, tags);
            } catch (NollidException e) {
                e.printStackTrace();
            }
            break;
        default:
            // Invalid task type, taskToAdd is not reassigned
            throw new NollidException("Task type not recognized.");
        }

        assert taskToAdd != null;

        boolean isDone = jsonObject.getBoolean("isDone");
        if (isDone) {
            taskToAdd.setDone(true);
        }

        return taskToAdd;
    }

    /**
     * Converts a Task object into a JSONObject for storage.
     *
     * @param task The Task object to be converted.
     * @return The JSONObject representing the Task.
     */
    private JSONObject getJsonObjectFromTask(Task task) {
        JSONObject jsonObject = new JSONObject();

        if (task instanceof Todo) {
            Todo todo = (Todo) task;
            jsonObject.put("type", "todo");
            jsonObject.put("isDone", todo.isDone());
            jsonObject.put("description", todo.getDescription());
            jsonObject.put("tags", todo.getTags());
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            jsonObject.put("type", "deadline");
            jsonObject.put("isDone", deadline.isDone());
            jsonObject.put("description", deadline.getDescription());
            jsonObject.put("deadline", deadline.getDeadline().format(Parser.SAVE_FORMAT));
            jsonObject.put("tags", deadline.getTags());

        } else if (task instanceof Event) {
            Event event = (Event) task;
            jsonObject.put("type", "event");
            jsonObject.put("isDone", event.isDone());
            jsonObject.put("description", event.getDescription());
            jsonObject.put("from", event.getFrom().format(Parser.SAVE_FORMAT));
            jsonObject.put("to", event.getTo().format(Parser.SAVE_FORMAT));
            jsonObject.put("tags", event.getTags());
        }

        return jsonObject;
    }

    /**
     * Loads a JSONArray from the specified file path.
     *
     * @return The loaded JSONArray.
     * @throws IOException   If there is an issue reading the file.
     * @throws JSONException If there is an issue parsing the JSON data.
     */
    private JSONArray loadJsonArray() throws IOException, JSONException {
        // If file doesn't exist, create it.
        if (Files.notExists(this.filePath)) {
            if (Files.notExists(this.filePath.getParent())) {
                Files.createDirectories(this.filePath.getParent());
            }
            Files.createFile(this.filePath);
        }

        JSONTokener jsonTokener = new JSONTokener(Files.newInputStream(this.filePath));

        return new JSONArray(jsonTokener);
    }

    /**
     * Retrieves tags from the JSONObject.
     *
     * @param jsonObject The JSONObject representing a Task.
     * @return The list of tags.
     */
    private ArrayList<String> getTagsFromJsonObject(JSONObject jsonObject) {
        JSONArray tagsArray;
        ArrayList<String> tags = new ArrayList<>();
        try {
            tagsArray = jsonObject.getJSONArray("tags");
        } catch (JSONException e) {
            e.printStackTrace();
            return tags;
        }

        for (Object tag : tagsArray) {
            if (tag instanceof String) {
                tags.add((String) tag);
            }
        }

        return tags;
    }
}
