package solaire.storage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import solaire.data.exception.SolaireException;
import solaire.data.task.Deadline;
import solaire.data.task.Event;
import solaire.data.task.Task;
import solaire.data.task.Todo;

/**
 * Represents a local storage as a utility class.
 * Reading from and writing to local storage is handled here.
 */
public class Storage {
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Loads data from a pre-determined file path and generates a list of tasks for UI.
     *
     * @return an <code>ArrayList</code> of <code>Task</code> objects.
     */
    public static ArrayList<Task> loadFromLocal() {
        // Clear current tasklist
        taskList.clear();
        assert taskList.isEmpty() : "Tasklist should be empty";
        // Read from target file
        Path filePath = Paths.get("src", "main", "resources", "Solaire.txt");
        try {

            Path directoryPath = filePath.getParent();
            // Check if directory exists
            Files.createDirectories(directoryPath);

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            List<String> lines = Files.readAllLines(filePath);

            for (String line : lines) {
                parseTasks(line);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong in reading local data: " + e.getMessage());
        } catch (SolaireException e) {
            throw new RuntimeException(e);
        }

        return taskList;
    }

    /**
     * Formats the given taskList items and writes them into local file in pre-determined path.
     *
     * @param taskList an <code>ArrayList</code> of <code>Task</code> objects
     */
    public static void write(ArrayList<Task> taskList) {
        Path filePath = Paths.get("src", "main", "resources", "Solaire.txt");
        try {
            Files.createDirectories(filePath.getParent());
            Storage.taskList = taskList;

            BufferedWriter bw = Files.newBufferedWriter(filePath);

            for (Task task : taskList) {
                String content = "";
                if (task instanceof Todo) {
                    content = "T | " + (task.getIsDone() ? "1" : "0") + " | " + (task.getDescription());
                } else if (task instanceof Deadline) {
                    Deadline ddlTask = (Deadline) task;
                    content = "D | " + (ddlTask.getIsDone() ? "1" : "0") + " | " + (ddlTask.getDescription()) + " | "
                            + (ddlTask.getDeadline());
                } else if (task instanceof Event) {
                    Event eventTask = (Event) task;
                    content = "E | " + (eventTask.getIsDone() ? "1" : "0") + " | " + (eventTask.getDescription())
                            + " | " + (eventTask.getStart()) + " | " + (eventTask.getEnd());
                }
                if (!content.equals("")) {
                    bw.write(content);
                    bw.newLine();
                }
            }
            bw.close();

        } catch (IOException e) {
            System.out.println("Something went wrong on attempting to write to local disk: " + e.getMessage());
        }
    }

    private static void parseTasks(String line) throws SolaireException {
        assert !line.isEmpty() : "Line should not be empty";
        String[] taskDetails = line.split(" \\| ");
        String taskType = taskDetails[0].trim();
        Boolean isComplete = taskDetails[1].trim().equals("1") ? true : false;
        String taskDescription = taskDetails[2].trim();

        switch (taskDetails.length) {
        case 3: {
            Task newTask = new Todo(taskDescription);
            if (isComplete) {
                newTask.markAsDone();
            }
            taskList.add(newTask);
            break;
        }
        case 4: {
            Task newTask;
            if (taskType.trim().equals("D")) {
                newTask = new Deadline(taskDescription, taskDetails[3]);
            } else {
                String[] timeDetails = taskDetails[3].split("\\-");
                newTask = new Event(taskDescription, timeDetails[0], timeDetails[1]);
            }
            if (isComplete) {
                newTask.markAsDone();
            }
            taskList.add(newTask);
            break;
        }
        default:
            throw new SolaireException("Invalid task format in local storage");
        }
    }
}
