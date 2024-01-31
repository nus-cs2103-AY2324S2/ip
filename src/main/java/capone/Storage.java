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

public class Storage {
    private final String jsonFilename;
    private final String jsonFilepath;

    public Storage(String path, String name) {
        this.jsonFilepath = path;
        this.jsonFilename = name;
    }

    private String getFullPath() {
        return this.jsonFilepath + this.jsonFilename;
    }

    private String getFileName() {
        return this.jsonFilename;
    }

    private String getFilePath() {
        return this.jsonFilepath;
    }

    public void writeTasksToJsonFile(TaskList taskList) {
        JSONArray jsonArray = new JSONArray();

        for (Task task : taskList) {
            JSONObject jsonTask = new JSONObject();
            jsonTask.put("taskType", task.getTaskType());
            jsonTask.put("description", task.getDescription());
            jsonTask.put("status", task.getStatus());

            // Add type-specific information for capone.tasks.Deadline and capone.tasks.Event tasks
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

    public void readTasksFromJsonFile(TaskList taskList) throws TaskListCorruptedException {
        try {
            this.initFileIfNotExist();

            String jsonContent = new String(Files.readAllBytes(Paths.get(this.getFullPath())));
            JSONArray jsonArray = new JSONArray(jsonContent);

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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            initFileIfNotExist();
            throw new TaskListCorruptedException("capone.tasks.Task list file is corrupted."
                    + " Creating new task list file.");
        }
    }

    public void initFileIfNotExist() {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
