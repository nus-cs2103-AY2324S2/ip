package charlie.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import charlie.exceptions.CharlieException;
import charlie.models.Deadline;
import charlie.models.Event;
import charlie.models.Task;
import charlie.models.Todo;

public class Storage {

    private String filePath;

    /**
     * creates a storage file (storage space) within which the list of tasks / user input can be saved
     * @param filePath path within the current directory by which the storage file should be saved
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * loads tasks using the given filePath
     * @return tasks loaded from the filePath
     * @throws CharlieException
     */
    public ArrayList<Task> loadTasks() throws CharlieException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    loadedTasks.add(parseTaskFromFile(line));
                } catch (IllegalArgumentException e) {
                    throw new CharlieException("Found a corrupted task in save file, skipping: " + line);
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new CharlieException("No saved tasks found, starting with an empty list.");
        }
        return loadedTasks;
    }

    /**
     * The parseTaskFromFile method converts a single line of text into a specific Task object
     * (to-Do, Deadline, or Event) based on the line's format, throwing an exception for unrecognized
     * task types
     * @param line line read from the storage file
     * @return the task object
     * @throws IllegalArgumentException
     */
    private Task parseTaskFromFile(String line) throws IllegalArgumentException {
        String[] parts = line.split(" \\| ");
        Task task = null;

        switch (parts[0]) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            default:
                throw new IllegalArgumentException("Invalid task type in file.");
        }

        if (parts[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * saves the list of tasks to the storage file
     * @param taskList lists of tasks to be saved
     * @throws CharlieException
     */
    public void saveTasks(ArrayList<Task> taskList) throws CharlieException {
        try {
            Files.createDirectories(Paths.get("./data"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            for (Task task : taskList) {
                writer.write(taskToFileFormat(task));
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            throw new CharlieException("An error occurred while saving tasks to disk.");
        }
    }

    /**
     * converts tasks to format required to be written within files
     * @param task task to be converted into format for saving within the file
     * @return string of how the task should appear in file format
     */
    private String taskToFileFormat(Task task) {
        String type = task instanceof Todo ? "T"
                : task instanceof Deadline ? "D"
                : task instanceof Event ? "E"
                : "";
        int done = task.isDone() ? 1 : 0;
        String details = task.getDescription();

        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            details += " | " + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            details += " | " + event.getStartsAt() + " | " + event.getEndsAt();
        }

        return String.join(" | ", type, String.valueOf(done), details);
    }
}
