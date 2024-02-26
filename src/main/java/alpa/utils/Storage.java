package alpa.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import alpa.exceptions.AlpaException;
import alpa.tasks.Deadline;
import alpa.tasks.Event;
import alpa.tasks.Task;
import alpa.tasks.TaskType;
import alpa.tasks.ToDo;

/**
 * The Storage class is responsible for loading and saving tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file to be managed by the storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        ensureDirectoryExists();
    }

    /**
     * Ensures that the directory containing the file path exists.
     */
    private void ensureDirectoryExists() {
        File directory = new File(filePath).getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Loads tasks from a file and returns them as a list.
     *
     * @return The list of loaded tasks.
     * @throws AlpaException If the file is not found or an error occurs while reading the file.
     */
    public List<Task> loadTasks() throws AlpaException {
        List<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = parseLineToTask(line);
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new AlpaException("\nFile not found human..." + e.getMessage());
        }
        return loadedTasks;
    }

    /**
     * Parses a line from the file to a Task object.
     *
     * @param line The line to be parsed.
     * @return The Task object parsed from the line.
     * @throws AlpaException If an error occurs while parsing the line.
     */
    private Task parseLineToTask(String line) throws AlpaException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new AlpaException("Invalid line format: " + line);
        }

        TaskType taskType = TaskType.fromShortName(parts[0]);
        boolean isDone = "1".equals(parts[1]);
        String description = parts[2];
        Task task;

        switch (taskType) {
        case TODO:
            task = parseToDoTask(description, isDone);
            break;
        case DEADLINE:
            task = parseDeadlineTask(parts, isDone);
            break;
        case EVENT:
            task = parseEventTask(parts, isDone);
            break;
        default:
            throw new AlpaException("Unrecognized task type: " + taskType);
        }
        return task;
    }

    /**
     * Parses a line from the file to a ToDo object.
     *
     * @param description The description of the task.
     * @param isDone     Whether the task is done.
     * @return The ToDo object parsed from the line.
     */
    private ToDo parseToDoTask(String description, boolean isDone) {
        ToDo task = new ToDo(description);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Parses a line from the file to a Deadline object.
     *
     * @param parts The parts of the line to be parsed.
     * @param isDone Whether the task is done.
     * @return The Deadline object parsed from the line.
     * @throws AlpaException If an error occurs while parsing the line.
     */
    private Deadline parseDeadlineTask(String[] parts, boolean isDone) throws AlpaException {
        if (parts.length < 4) {
            throw new AlpaException("Invalid deadline task format.");
        }
        LocalDateTime time = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Deadline task = new Deadline(parts[2], time);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Parses a line from the file to an Event object.
     *
     * @param parts The parts of the line to be parsed.
     * @param isDone Whether the task is done.
     * @return The Event object parsed from the line.
     * @throws AlpaException If an error occurs while parsing the line.
     */
    private Event parseEventTask(String[] parts, boolean isDone) throws AlpaException {
        if (parts.length < 5) {
            throw new AlpaException("Invalid event task format.");
        }
        LocalDateTime start = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime end = LocalDateTime.parse(parts[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Event task = new Event(parts[2], start, end);
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws AlpaException If an error occurs while saving the tasks.
     */
    public void saveTasks(List<Task> tasks) throws AlpaException {
        try (FileWriter fw = new FileWriter(filePath);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            for (Task task : tasks) {
                out.println(task.toFileFormat());
            }
        } catch (IOException e) {
            throw new AlpaException("\nError! Could not save tasks!" + e.getMessage());
        }
    }
}
