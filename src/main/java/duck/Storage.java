
package duck;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The Storage class handles the saving and loading of tasks from a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path to the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks to the data file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws IOException If an error occurs while saving the file.
     */
    public void saveTasksToFile(TaskList tasks) throws IOException {
        Path filePath = Paths.get(this.filePath);

        // Create the parent directory if it doesn't exist
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        List<String> encodedTasks = TaskListEncoder.encode(tasks.getTasks());
        // Write tasks to the file
        Files.write(filePath, encodedTasks);
    }

    /**
     * Loads tasks from the data file.
     *
     * @return The loaded TaskList.
     * @throws IOException              If an error occurs while reading the file.
     * @throws DukeDataCorruptedException If the data file is corrupted.
     */
    public TaskList loadTasksFromFile() throws IOException, DukeDataCorruptedException {
        Path filePath = Paths.get(this.filePath);

        TaskList tasks;

        // Check if the file exists before attempting to load
        if (Files.exists(filePath)) {
            try {
                // Read tasks from the file
                List<String> lines = Files.readAllLines(filePath);
                tasks = new TaskList(TaskListDecoder.decode(lines));
            } catch (FileNotFoundException e) {
                // If the file is not found, throw a more specific exception
                throw new FileNotFoundException("Data file not found");
            } catch (IOException e) {
                throw new IOException("Error reading tasks from the file.", e);
            } catch (DukeDataCorruptedException e) {
                // Rethrow other exceptions, including DukeDataCorruptedException
                throw e;
            }
        } else {
            tasks = new TaskList(); // Initialize with an empty list if the file doesn't exist yet
        }

        return tasks;
    }

    /**
     * Ensures that the data file exists; if not, it creates the file.
     *
     * @throws IOException If an error occurs while creating the file.
     */
    public void ensureDataFileExists() throws IOException {
        Path filePath = Paths.get(this.filePath);

        // Check if the file exists or create it
        if (!Files.exists(filePath)) {
            // Check if the parent directory exists, create it if not
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }

            // Create the data file
            Files.createFile(filePath);
        }
    }
}

/**
 * The TaskListEncoder class encodes a list of tasks into a format suitable for saving to a file.
 */
class TaskListEncoder {
    /**
     * Encodes a list of tasks into a list of strings.
     *
     * @param tasks The list of tasks to encode.
     * @return The encoded list of strings.
     */
    public static List<String> encode(List<Task> tasks) {
        List<String> encodedTasks = new ArrayList<>();
        for (Task task : tasks) {
            encodedTasks.add(encodeTask(task));
        }
        return encodedTasks;
    }

    /**
     * Encodes a single task into a string representation.
     *
     * @param task The task to encode.
     * @return The encoded string representation of the task.
     */
    private static String encodeTask(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + task.getTag();
        } else if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            String by = deadlineTask.getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return "D | " + (task.isDone() ? "1" : "0") + " | " + deadlineTask.getDescription() + " | " + "by " + by
                    + " | " + task.getTag();
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            String from = eventTask.getFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String to = eventTask.getTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return "E | " + (task.isDone() ? "1" : "0") + " | "
                    + eventTask.getDescription() + " | " + from + " to " + to + " | " + task.getTag();
        } else {
            // Handle other task types if needed
            return "";
        }
    }
}

/**
 * The TaskListDecoder class decodes a list of strings from a file into a list of tasks.
 */
class TaskListDecoder {
    /**
     * Decodes a list of strings into a list of tasks.
     *
     * @param lines The list of strings to decode.
     * @return The decoded list of tasks.
     * @throws DukeDataCorruptedException If the data file is corrupted.
     */
    public static List<Task> decode(List<String> lines) throws DukeDataCorruptedException {
        List<Task> decodedTasks = new ArrayList<>();
        try {
            for (String line : lines) {
                decodedTasks.add(decodeTask(line));
            }
        } catch (IllegalArgumentException e) {
            throw new DukeDataCorruptedException("Data file is corrupted: " + e.getMessage());
        }

        return decodedTasks;
    }

    /**
     * Decodes a single string representation of a task into a Task object.
     *
     * @param line The string representation of the task.
     * @return The decoded Task object.
     */
    private static Task decodeTask(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1"); // Check if the task is marked as done
        String description = parts[2];

        switch (taskType) {
        case "T":
            return decodeTodoTask(description, isDone, parts);
        case "D":
            return decodeDeadlineTask(description, isDone, parts);
        case "E":
            return decodeEventTask(description, isDone, parts);
        default:
            return null;
        }
    }

    /**
     * Decodes a Todo task from string representation.
     *
     * @param description The description of the task.
     * @param isDone      Whether the task is marked as done.
     * @param parts       The array of string parts from the input line.
     * @return The decoded Todo task object.
     */
    private static Task decodeTodoTask(String description, boolean isDone, String[] parts) {
        Task newTodo = new Todo(description);
        newTodo.setDone(isDone);
        if (parts.length >= 4) {
            newTodo.setTag(parts[3]);
        }
        return newTodo;
    }

    /**
     * Decodes a Deadline task from string representation.
     *
     * @param description The description of the task.
     * @param isDone      Whether the task is marked as done.
     * @param parts       The array of string parts from the input line.
     * @return The decoded Deadline task object.
     */
    private static Task decodeDeadlineTask(String description, boolean isDone, String[] parts) {
        String by = parts[3];
        LocalDate byDateTime = LocalDate.parse(by.substring(3), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Task newDeadline = new Deadline(description, byDateTime);
        newDeadline.setDone(isDone);
        if (parts.length >= 5) {
            newDeadline.setTag(parts[4]);
        }
        return newDeadline;
    }

    /**
     * Decodes an Event task from string representation.
     *
     * @param description The description of the task.
     * @param isDone      Whether the task is marked as done.
     * @param parts       The array of string parts from the input line.
     * @return The decoded Event task object.
     */
    private static Task decodeEventTask(String description, boolean isDone, String[] parts) {
        String dateTimeString = parts[3];
        String[] dateTimeParts = dateTimeString.split(" to ");
        String from = dateTimeParts[0];
        String to = dateTimeParts[1];

        LocalDate fromDateTime = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate toDateTime = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Task newEvent = new Event(description, fromDateTime, toDateTime);
        newEvent.setDone(isDone);

        if (parts.length >= 5) {
            newEvent.setTag(parts[4]);
        }
        return newEvent;
    }
}
