package georgie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;

/**
 * Handles the storage of tasks to and from a file.
 */
public class Storage {
    private static String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks to the file specified by the file path.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void saveTasksToFile(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file specified by the file path and adds them to the given task list.
     *
     * @param tasks The task list to which tasks will be added.
     */
    public void loadTasksFromFile(ArrayList<Task> tasks) {
        try {
            Path filePath = Paths.get(this.filePath);
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            } else {
                try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(filePath)))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        Task task = createTaskFromLine(line);
                        if (task != null) {
                            tasks.add(task);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    private Task createTaskFromLine(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 2) {
            return null;
        }

        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts.length > 2 ? parts[2].trim() : "";

        switch (taskType) {
            case "T":
                return new ToDo(description);
            case "D":
                String dueBy = parts.length > 3 ? parts[3].trim() : "";
                LocalDate dueByDate = parseDate(dueBy);
                return new Deadline(description, dueByDate);
            case "E":
                String start = parts.length > 3 ? parts[3].trim() : "";
                String end = parts.length > 4 ? parts[4].trim() : "";
                LocalDate startDate = parseDate(start);
                LocalDate endDate = parseDate(end);
                return new Event(description, startDate, endDate);
            default:
                return null;
        }
    }

    private static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }
}
