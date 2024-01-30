import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TaskStorage {
    private static final String JSON_FILENAME = "tasks.json";
    private static final String JSON_FILEPATH = "./data/";

    public static void writeTasksToJsonFile(List<Task> tasks) {
        JSONArray jsonArray = new JSONArray();

        for (Task task : tasks) {
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

        try (FileWriter fileWriter = new FileWriter(JSON_FILEPATH + JSON_FILENAME)) {
            jsonArray.write(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readTasksFromJsonFile(List<Task> tasks) throws TaskListCorruptedException {
        try {
            TaskStorage.initFileIfNotExist();

            String jsonContent = new String(Files.readAllBytes(Paths.get(JSON_FILEPATH + JSON_FILENAME)));
            JSONArray jsonArray = new JSONArray(jsonContent);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonTask = jsonArray.getJSONObject(i);

                String type = jsonTask.getString("taskType");
                String description = jsonTask.getString("description");
                boolean status = jsonTask.getBoolean("status");

                switch (type) {
                    case "todo":
                        tasks.add(new ToDo(description, status));
                        break;
                    case "deadline":
                        String deadline = jsonTask.getString("deadline");
                        tasks.add(new Deadline(description, status, deadline));
                        break;
                    case "event":
                        String fromDate = jsonTask.getString("fromDate");
                        String toDate = jsonTask.getString("toDate");
                        tasks.add(new Event(description, status, fromDate, toDate));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            initFileIfNotExist();
            throw new TaskListCorruptedException("Task list file is corrupted." +
                    " Creating new task list file.");
        }
    }

    public static void initFileIfNotExist() {
        // Create the folder if it doesn't exist
        File folder = new File(JSON_FILEPATH);
        if (!folder.exists()) {
            folder.mkdir();
        }

        // Create the file within the folder
        File file = new File(folder, JSON_FILENAME);

        if (!file.exists()) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write("[]");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
