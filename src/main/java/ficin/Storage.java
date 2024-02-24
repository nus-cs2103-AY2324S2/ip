package ficin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ficin.task.Deadline;
import ficin.task.Event;
import ficin.task.Task;
import ficin.task.Todo;

/**
 * The Storage class is responsible for managing the storage and retrieval of tasks in the Duke chat-bot application.
 * It handles saving tasks to a file and loading tasks from a file.
 */
public class Storage {
    private static final String FILE_PATH = "./data/duke.txt";

    /**
     * Saves a list of tasks to a file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void saveTasks(List<Task> tasks) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from a file and returns them as a list.
     *
     * @return A list of tasks loaded from the file.
     * @throws DukeException If there is an issue loading tasks from the file.
     */
    public static List<Task> loadTasks() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        createEmptyFile();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = createTaskFromLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Creates an empty file and directory if they do not exist.
     *
     * @throws DukeException If there is an issue creating the file or directory.
     */
    private static void createEmptyFile() throws DukeException {
        File directory = new File("./data");
        File file = new File("./data/duke.txt");

        if (!directory.exists()) {
            directory.mkdir();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("File Creation Failed");
            }
        }
    }

    /**
     * Creates a Task from a line of text read from the file.
     * This method creates and returns a specified Task subclass (Todo, Deadline, or Event)
     * by parsing a structured string representing a task and extracting pertinent information.
     *
     * @param line The formatted text line with separated sections that represents a task.
     * @return The Task created from the line, or null if the line cannot be break into a valid task.
     */
    private static Task createTaskFromLine(String line) {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length >= 3) {
            String taskType = parts[0].trim();
            String status = parts[1].trim();
            String description = parts[2].trim();

            Task task;
            switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length >= 4) {
                    String dateTimeString = parts[3].trim();
                    if (dateTimeString.contains(" ")) {
                        LocalDateTime byDateTime = LocalDateTime.parse(dateTimeString,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                        task = new Deadline(description, byDateTime);
                    } else {
                        LocalDate byDate = LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        task = new Deadline(description, byDate);
                    }
                } else {
                    task = null;
                }
                break;
            case "E":
                if (parts.length >= 4) {
                    String dateAndTime = parts[3].trim();
                    String[] event = dateAndTime.split("-");
                    if (event.length == 2) {
                        String start = event[0].trim();
                        String end = event[1].trim();
                        // Parse the event start and end times
                        LocalDateTime startTime = LocalDateTime.parse(start,
                                DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
                        LocalDateTime endTime = LocalDateTime.parse(end,
                                DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
                        task = new Event(description, startTime, endTime);
                    } else {
                        task = null;
                    }
                } else {
                    task = null;
                }
                break;
            default:
                task = null;
                break;
            }

            if (task != null) {
                if (status.equals("1")) {
                    task.markAsDone();
                }
                return task;
            }
        }
        return null;
    }
}
