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
        List<Task> tempTasks = new ArrayList<>();
        try {
            File file = new File(this.filePath);
            if (file.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line = br.readLine();
                    while (line != null) {
                        String[] taskStrings = line.split(" \\| ");
                        assert taskStrings.length >= 3 : "Invalid task format stored in file";
                        try {
                            TaskType taskType = TaskType.valueOf(taskStrings[0]);
                            Task task;
                            switch (taskType) {
                            case T:
                                task = new Todo(taskStrings[2], taskStrings[1].equals("1"));
                                break;
                            case D:
                                task = new Deadline(taskStrings[2], LocalDateTime.parse(taskStrings[3]),
                                        taskStrings[1].equals("1"));
                                break;
                            case E:
                                task = new Event(taskStrings[2], LocalDateTime.parse(taskStrings[3]),
                                        LocalDateTime.parse(taskStrings[4]), taskStrings[1].equals("1"));
                                break;
                            default:
                                throw new InvalidTaskTypeException();
                            }
                            tempTasks.add(task);
                            line = br.readLine();
                        } catch (IllegalArgumentException e) {
                            throw new InvalidTaskTypeException();
                        }
                    }
                }
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            return tempTasks;
        } catch (IOException e) {
            throw new LoadTasksException(this.filePath);
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
            File file = new File(this.filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            fw.write(tasks.toFileString());
            fw.close();
        } catch (IOException e) {
            throw new SaveTasksException(this.filePath);
        }
    }
}
