package duke;

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the file storage for Duke application, handling the loading and saving of tasks to a file.
 *
 * @author Qin Boan
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isEmpty();
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws DukeException If an error occurs during file processing.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    Task task = fileStringToTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
                scanner.close();
            }
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to the file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws DukeException If an error occurs during file processing.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            PrintWriter writer = new PrintWriter(filePath);

            for (int i = 0; i < tasks.size(); i++) {
                writer.println(taskToFileString(tasks.getTask(i)));
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Converts a line from the file to a Task object.
     *
     * @param line The line from the file to be converted into a Task.
     * @return The Task object represented by the line.
     * @throws DukeException If the line format is invalid.
     */
    private static Task fileStringToTask(String line) throws DukeException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new DukeException("Unable to read some tasks!");
        }
        String type = parts[0];
        boolean isDone = "1".equals(parts[1]);
        String description = parts[2];

        Task task = null;
        switch (type) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            task = new Deadline(description, parts.length > 3 ? parts[3] : "");
            break;
        case "E":
            task = new Event(description, parts.length > 3 ? parts[3] : "", parts.length > 4 ? parts[4] : "");
            break;
        case "F":
            task = new FixedDuration(description, parts.length > 3 ? parts[3] : "");
            break;
        }
        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Converts a Task object to a string for file storage.
     *
     * @param task The Task to be converted to a string.
     * @return The string representation of the Task for file storage.
     */
    private static String taskToFileString(Task task) {
        String type = task instanceof ToDo ? "T" :
                task instanceof Deadline ? "D" :
                        task instanceof Event ? "E" :
                                task instanceof FixedDuration ? "F" : "";
        String status = task.isDone ? "1" : "0";
        String details = type + " | " + status + " | " + task.description;

        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            details += " | " + deadline.getByForFile();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            details += " | " + event.getStartForFile() + " | " + event.getEndForFile();
        } else if (task instanceof FixedDuration) {
            FixedDuration fixed = (FixedDuration) task;
            details += " | " + fixed.duration;
        }

        return details;
    }
}
