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
     * Parses a line from the file to a task.
     *
     * @param line The line to be parsed.
     * @return The task parsed from the line.
     * @throws AlpaException If the line is in an invalid format.
     */
    private Task parseLineToTask(String line) throws AlpaException {
        try {
            String[] parts = line.split(" \\| ");

            if (parts.length < 3) {
                throw new AlpaException("\nInvalid line format in tasks file.");
            }

            TaskType taskType = TaskType.fromShortName(parts[0]);
            boolean isDone = "1".equals(parts[1]);
            Task task = null;

            switch (taskType) {
            case TODO:
                task = new ToDo(parts[2]);
                break;
            case DEADLINE:
                LocalDateTime deadlineDateTime = LocalDateTime.parse(parts[3],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                task = new Deadline(parts[2], deadlineDateTime);
                break;
            case EVENT:
                String[] dateTimeParts = parts[3].split(" - ");
                if (dateTimeParts.length != 2) {
                    throw new AlpaException("\nInvalid event time format, expected 'start - end'.");
                }
                LocalDateTime startDateTime = LocalDateTime.parse(dateTimeParts[0],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                LocalDateTime endDateTime = LocalDateTime.parse(dateTimeParts[1],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                task = new Event(parts[2], startDateTime, endDateTime);
                break;
            default:
                break;
            }

            if (task != null && isDone) {
                task.markAsDone();
            }
            return task;
        } catch (Exception e) {
            throw new AlpaException("Error parsing line to task: " + e.getMessage());
        }
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
