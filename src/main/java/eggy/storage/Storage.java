package eggy.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import eggy.exception.InvalidTaskTypeException;
import eggy.exception.LoadTasksException;
import eggy.exception.SaveTasksException;
import eggy.task.Deadline;
import eggy.task.Event;
import eggy.task.Task;
import eggy.task.TaskList;
import eggy.task.Todo;

/**
 * Represents the storage of the chatbot.
 */
public class Storage {
    /** The file path of the storage. */
    private final String filePath;

    /** The type of task. */
    private enum TaskType {
        T, D, E
    }

    /**
     * Constructs the storage of the chatbot.
     *
     * @param filePath The file path of the storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the storage.
     *
     * @return The list of tasks.
     * @throws InvalidTaskTypeException If the task type is invalid.
     * @throws LoadTasksException If there is an error loading the tasks.
     */
    public List<Task> load() throws InvalidTaskTypeException, LoadTasksException {
        try {
            File file = openFile();
            return readTasksFile(file);
        } catch (IOException e) {
            throw new LoadTasksException(this.filePath);
        }
    }

    /**
     * Reads the tasks from the storage file.
     *
     * @param file The storage file.
     * @return The list of tasks.
     * @throws IOException If there is an error reading the file.
     * @throws InvalidTaskTypeException If the task type is invalid.
     */
    private List<Task> readTasksFile(File file) throws IOException, InvalidTaskTypeException {
        List<Task> tempTasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                tempTasks.add(processTaskLine(line));
            }
        }
        return tempTasks;
    }

    /**
     * Processes a line from the storage file and returns the task.
     *
     * @param line The line from the storage file.
     * @return The task object.
     * @throws InvalidTaskTypeException If the task type is invalid.
     */
    private Task processTaskLine(String line) throws InvalidTaskTypeException {
        String[] taskStrings = line.split(" \\| ");
        assert taskStrings.length >= 3 : "Invalid task format stored in file";
        try {
            TaskType taskType = TaskType.valueOf(taskStrings[0]);
            return createTask(taskType, taskStrings);
        } catch (IllegalArgumentException e) {
            throw new InvalidTaskTypeException();
        }
    }

    /**
     * Creates a task object from the task type and task strings.
     *
     * @param taskType The type of task.
     * @param taskStrings The strings of the task.
     * @return The task object.
     * @throws InvalidTaskTypeException If the task type is invalid.
     */
    private Task createTask(TaskType taskType, String[] taskStrings) throws InvalidTaskTypeException {
        switch (taskType) {
        case T:
            return new Todo(taskStrings[2], taskStrings[1].equals("1"));
        case D:
            return new Deadline(taskStrings[2], LocalDateTime.parse(taskStrings[3]),
                    taskStrings[1].equals("1"));
        case E:
            return new Event(taskStrings[2], LocalDateTime.parse(taskStrings[3]),
                    LocalDateTime.parse(taskStrings[4]), taskStrings[1].equals("1"));
        default:
            throw new InvalidTaskTypeException();
        }
    }

    /**
     * Saves the tasks to the storage.
     *
     * @param tasks The list of tasks.
     * @throws SaveTasksException If there is an error saving the tasks.
     */
    public void save(TaskList tasks) throws SaveTasksException {
        assert tasks != null : "Task list should not be null";
        try {
            File file = openFile();
            FileWriter fw = new FileWriter(file);
            fw.write(tasks.toFileString());
            fw.close();
        } catch (IOException e) {
            throw new SaveTasksException(this.filePath);
        }
    }

    /**
     * Opens the storage file. If the file does not exist, it will be created.
     *
     * @return The storage file.
     * @throws IOException If there is an error opening the file.
     */
    private File openFile() throws IOException {
        File file = new File(this.filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return file;
    }
}
