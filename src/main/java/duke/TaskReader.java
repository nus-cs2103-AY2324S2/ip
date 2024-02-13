package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;

/**
 * Reads tasks from a file and populates a task list.
 */
public class TaskReader {

    /**
     * Loads tasks from the file specified by the file path and adds them to the given task list.
     *
     * @param tasks    The task list to which tasks will be added.
     * @param filePath The file path from which tasks will be loaded.
     */
    public static void loadTasksFromFile(ArrayList<Task> tasks, String filePath) {
        createDirectory(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = createTaskFromLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    private static Task createTaskFromLine(String line) {
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
                return new Event(description, start, end);
            default:
                return null;
        }
    }

    private static void createDirectory(String filePath) {
        File file = new File(filePath);
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            if (!directory.mkdirs()) {
                System.out.println("Error creating directory: " + directory.getAbsolutePath());
            }
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