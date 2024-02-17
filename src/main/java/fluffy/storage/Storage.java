package fluffy.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import fluffy.FluffyException;
import fluffy.task.Deadline;
import fluffy.task.Event;
import fluffy.task.Task;
import fluffy.task.Todo;
import fluffy.tasklist.TaskList;

/**
 * Represents a storage for saving and loading tasks to and from the hard disk.
 */
public class Storage {
    protected String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath The file path of the storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves data to the hard disk.
     *
     * @param tasks The list of tasks to be saved.
     * @throws FluffyException If an error occurs during the saving of the tasks.
     */
    public void save(TaskList tasks) throws FluffyException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                fileWriter.write(encodeTask(task) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new FluffyException("Error writing to file");
        }
    }

    /**
     * Loads data from the hard disk.
     *
     * @return The list of tasks loaded from the hard disk.
     * @throws FluffyException If an error occurs during the loading of the tasks.
     */
    public List<Task> load() throws FluffyException {
        List<Task> tasks = new ArrayList<>();
        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                tasks.add(decodeTask(line));
            }
        } catch (IOException e) {
            throw new FluffyException("Error reading from file");
        }
        return tasks;
    }

    /**
     * Encodes task to a String for saving to the hard disk.
     * Prepends the type of task to the task's fileString.
     *
     * @param task the Task to be encoded.
     * @return Encoded task as a String.
     */
    public String encodeTask(Task task) {
        return task.getType() + " | " + task.toFileString();
    }

    /**
     * Decodes task from a String read from the hard disk.
     * Extracts the type of task from the encoded task String and creates the task accordingly.
     *
     * @param encodedTaskString the encoded task String read from the hard disk.
     * @return Decoded task.
     * @throws FluffyException If an error occurs during the decoding of the task.
     */
    public Task decodeTask(String encodedTaskString) throws FluffyException {
        String[] taskDetails = encodedTaskString.split(" \\| ", 2);
        String taskType = taskDetails[0];
        String taskFileString = taskDetails[1];
        switch (taskType) {
        case "T":
            return Todo.todoFromFileString(taskFileString);
        case "D":
            return Deadline.deadlineFromFileString(taskFileString);
        case "E":
            return Event.eventFromFileString(taskFileString);
        default:
            throw new FluffyException("Error reading from file");
        }
    }
}
