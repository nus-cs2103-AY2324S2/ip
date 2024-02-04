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
    public static final Path TEST_FILEPATH = Paths.get(".", "data", "test.json");
    private final Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a List of Tasks from the nollid.Storage's file path.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        // If file doesn't exist, create file and return empty taskList.
        try {
            if (Files.notExists(this.filePath)) {
                if (Files.notExists(this.filePath.getParent())) {
                    Files.createDirectories(this.filePath.getParent());
                }
                Files.createFile(this.filePath);

                return taskList;
            }
        } catch (IOException e) {
            return taskList;
        }

        JSONTokener jsonTokener;
        // If file unable to be read, return empty taskList.
        try {
            jsonTokener = new JSONTokener(Files.newInputStream(this.filePath));
        } catch (IOException e) {
            return taskList;
        }

        JSONArray jsonArray;
        // If file not in proper JSON format, return empty taskList.
        try {
            jsonArray = new JSONArray(jsonTokener);
        } catch (JSONException e) {
            return taskList;
        }

        Task taskToAdd;

        for (int i = 0; i < jsonArray.length(); i++) {
            // If for any reason, a JSON object is unable to be read in the file, just go to the next object.
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String taskType = jsonObject.getString("type");
                String description = jsonObject.getString("description");


                switch (taskType) {
                case "todo":
                    taskToAdd = new Todo(description);
                    break;
                case "deadline":
                    LocalDateTime deadline = Parser.getLocalDateTimeFromString(jsonObject.getString("deadline"));
                    taskToAdd = new Deadline(description, deadline);
                    break;
                case "event":
                    LocalDateTime from = Parser.getLocalDateTimeFromString(jsonObject.getString("from"));
                    LocalDateTime to = Parser.getLocalDateTimeFromString(jsonObject.getString("to"));
                    try {
                        taskToAdd = new Event(description, from, to);
                    } catch (NollidException e) {
                        e.printStackTrace();
                        continue;
                    }
                    break;
                default:
                    // Invalid task, go to next object
                    continue;
                }

                boolean isDone = jsonObject.getBoolean("isDone");

                if (isDone) {
                    taskToAdd.setDone(true);
                }
                taskList.add(taskToAdd);
            } catch (JSONException e) {
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
            JSONObject jsonObject = new JSONObject();

            if (t instanceof Todo) {
                Todo todo = (Todo) t;
                jsonObject.put("type", "todo");
                jsonObject.put("isDone", todo.isDone());
                jsonObject.put("description", todo.getDescription());
            } else if (t instanceof Deadline) {
                Deadline deadline = (Deadline) t;
                jsonObject.put("type", "deadline");
                jsonObject.put("isDone", deadline.isDone());
                jsonObject.put("description", deadline.getDescription());
                jsonObject.put("deadline", deadline.getDeadline().format(Parser.SAVE_FORMAT));
            } else if (t instanceof Event) {
                Event event = (Event) t;
                jsonObject.put("type", "event");
                jsonObject.put("isDone", event.isDone());
                jsonObject.put("description", event.getDescription());
                jsonObject.put("from", event.getFrom().format(Parser.SAVE_FORMAT));
                jsonObject.put("to", event.getTo().format(Parser.SAVE_FORMAT));
            }

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
}
