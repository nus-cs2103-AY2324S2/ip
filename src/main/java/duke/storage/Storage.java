package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.storage.CreateTasksFileFailedException;
import duke.exceptions.storage.LoadTasksFailedException;
import duke.exceptions.storage.SaveTasksFailedException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/**
 * Handles loading and saving tasks to and from a file.
 * This class provides functionality to read tasks from a file upon starting the application
 * and to save tasks into the file upon exiting.
 */
public class Storage {
    private final Path filePath;
    private final String argDelimiter;

    /**
     * Constructs a Storage object with a specified file path and argument delimiter.
     *
     * @param filePath The file path where tasks are stored.
     * @param argDelimiter The delimiter used to separate task attributes in the file.
     */
    public Storage(Path filePath, String argDelimiter) {
        this.filePath = filePath;
        this.argDelimiter = argDelimiter;
    }

    /**
     * Ensures the file for storing tasks exists. If it does not, attempts to create it.
     *
     * @throws DukeException If creating the file or directories fails.
     */
    private void createFileIfDontExist() throws DukeException {
        try {
            if (!Files.exists(this.filePath)) {
                Path parentDir = this.filePath.getParent();
                if (!Files.exists(parentDir)) {
                    Files.createDirectories(parentDir);
                }
                Files.createFile(this.filePath);
            }
        } catch (IOException ioException) {
            throw new CreateTasksFileFailedException(ioException);
        }
    }

    /**
     * Parses task arguments into a Task object.
     *
     * @param taskArgs The array of strings representing task arguments.
     * @return A Task object if parsing is successful, null otherwise.
     */
    private Task parseTask(String[] taskArgs) throws Exception {
        String taskType = taskArgs[0];
        Task task;
        String description;
        boolean isDone;

        switch (taskType) {
        case "deadline":
            description = taskArgs[1];
            LocalDate by = LocalDate.parse(taskArgs[2]);
            isDone = taskArgs[3].equals("1");
            task = new Deadline(description, isDone, by);
            break;
        case "event":
            description = taskArgs[1];
            LocalDate from = LocalDate.parse(taskArgs[2]);
            LocalDate to = LocalDate.parse(taskArgs[3]);
            isDone = taskArgs[4].equals("1");
            task = new Event(description, isDone, from, to);
            break;
        case "todo":
            description = taskArgs[1];
            isDone = taskArgs[2].equals("1");
            task = new Todo(description, isDone);
            break;
        default:
            throw new LoadTasksFailedException("Unknown task type.");
        }

        return task;
    }

    /**
     * Reads tasks from the file and constructs a list of tasks from it.
     *
     * @return A list of tasks loaded from the file.
     * @throws DukeException If reading from the file fails or the file format is incorrect.
     */
    private ArrayList<Task> readTasks() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            String fileContent = Files.readString(this.filePath);
            String[] fileContentSplit = fileContent.split("\n");

            for (String taskArgsStr : fileContentSplit) {
                String[] taskArgs = taskArgsStr.split(this.argDelimiter);
                Task task = parseTask(taskArgs);

                tasks.add(task);
            }
            return tasks;
        } catch (Exception exception) {
            throw new LoadTasksFailedException(exception);
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws DukeException If the file does not exist and cannot be created, or if tasks cannot be loaded.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        this.createFileIfDontExist();
        return this.readTasks();
    }

    /**
     * Saves the current list of tasks to the file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws DukeException If saving the tasks to the file fails.
     */
    public void saveTasks(TaskList tasks) throws DukeException {
        try {
            ArrayList<String> tasksData = new ArrayList<>();
            for (int i = 0; i < tasks.size(); i++) {
                tasksData.add(tasks.get(i).serialize());
            }
            Files.writeString(this.filePath, String.join("\n", tasksData));
        } catch (IOException ioException) {
            throw new SaveTasksFailedException(ioException);
        }
    }
}
