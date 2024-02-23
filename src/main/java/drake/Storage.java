package drake;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import drake.task.Deadline;
import drake.task.Event;
import drake.task.Task;
import drake.task.Todo;

/**
 * Handles storage operations for tasks, such as saving to and loading from a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage object.
     *
     * @param filePath The file path where tasks will be saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;

        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the list of tasks to the file.*
     * @param tasks The ArrayList of Task objects to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Task task : tasks) {
                bufferedWriter.write(task.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads and returns the list of tasks from the file.
     * If the file does not exist, returns an empty list.
     * If the file exists but an error occurs during reading, also returns an empty list.
     * @return The ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasksLoaded = new ArrayList<>();
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                List<String> retrieved = Files.readAllLines(path);
                for (String line : retrieved) {
                    Task task = convertToTask(line);
                    tasksLoaded.add(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasksLoaded;
    }

    /**
     * Converts a serialized string representation of a task into a Task object.
     * The method supports conversion for three types of tasks: Todo, Deadline, and Event.
     * The string format for each type is as follows:
     * - Todo: "[T] [status] description", where status is 'X' for done and ' ' for not done.
     * - Deadline: "[D] [status] description (by: MMM dd yyyy)", where the date follows the "MMM dd yyyy" pattern.
     * - Event: "[E] [status] description (from: MMM dd yyyy to: MMM dd yyyy)",
     * with both dates following the "MMM dd yyyy" pattern.
     * The method identifies the task type by the prefix ("[T]", "[D]", or "[E]"), extracts the relevant information,
     * and creates the corresponding Task object (Todo, Deadline, or Event)
     * with the status and dates parsed as necessary.
     *
     * @param line The string representation of a task to be converted.
     * @return A Task object corresponding to the input string.
     */
    private Task convertToTask(String line) {
        boolean isDone = line.charAt(4) == 'X';
        Task task = null;
        if (line.startsWith("[T]")) {
            String description = line.substring(7);
            Todo todo = new Todo(description);
            if (isDone) {
                todo.markAsDone();
            }
            task = todo;
        } else if (line.startsWith("[D]")) {
            int indexBy = line.indexOf("(by: ");
            String description = line.substring(6, indexBy - 1).trim();
            String byString = line.substring(indexBy + 5, line.length() - 1);
            LocalDateTime by = LocalDate.parse(byString,
                    DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH)).atStartOfDay();
            Deadline deadline = new Deadline(description, by);
            if (isDone) {
                deadline.markAsDone();
            }
            task = deadline;
        } else if (line.startsWith("[E]")) {
            int indexFrom = line.indexOf("(from: ");
            int indexTo = line.indexOf("to: ");
            String description = line.substring(7, indexFrom - 2).trim();
            String fromString = line.substring(indexFrom + 6, indexTo - 1).trim();
            String toString = line.substring(indexTo + 4, line.length() - 1).trim();
            LocalDateTime from = LocalDate.parse(fromString,
                    DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH)).atStartOfDay();
            LocalDateTime to = LocalDate.parse(toString,
                    DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH)).atStartOfDay();
            Event event = new Event(description, from, to);
            if (isDone) {
                event.markAsDone();
            }
            task = event;
        }

        return task;
    }
}
