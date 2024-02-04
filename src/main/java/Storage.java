import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/*
 * The Storage class is used to read and write data to the hard disk.
 */
public class Storage {
    protected String filePath;

    /*
     * Constructs Storage object with filePath as a String.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /*
     * Writes data to the hard disk.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                fileWriter.write(encodeTask(task) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Error writing to file");
        }
    }

    /*
     * Reads data from the hard disk.
     */
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                tasks.add(decodeTask(line));
            }
        } catch (IOException e) {
            throw new DukeException("Error reading from file");
        }
        return tasks;
    }

    /*
     * Encodes task to a String for saving to the hard disk.
     * Prepends the type of task to the task's fileString.
     *
     * @param task the Task to be encoded.
     * @return Encoded task as a String.
     */
    public String encodeTask(Task task) {
        return task.getType() + " | " + task.toFileString();
    }

    public Task decodeTask(String encodedTaskString) throws DukeException{
        String[] taskDetails = encodedTaskString.split(" \\| ", 2);
        String taskType = taskDetails[0];
        String taskFileString = taskDetails[1];
        switch (taskType) {
            case "T":
                return Todo.TodoFromFileString(taskFileString);
            case "D":
                return Deadline.DeadlineFromFileString(taskFileString);
            case "E":
                return Event.EventFromFileString(taskFileString);
            default:
                throw new DukeException("Error reading from file");
        }
    }
}