package duke;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Storage class load and save the task from a file.
 * It provides methods to read and write the tasks
 *
 */
public class Storage {
    private String filePath = "/Users/leedoye/ip/src/data/duke_tasks.txt";

    /**
     * Constructs a Storage instance with a specified file path.
     *
     * @param filePath The path to the file for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the filepath.
     *
     * @return The list of tasks loaded from the file.
     * @throws DukeException If there is an error loading tasks from storage.
     */
    public List<Task> load() throws DukeException{
        try (ObjectInputStream intput = new ObjectInputStream(new FileInputStream("filePath"))) {
            return (List<Task>) intput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing tasks or file.");
            throw new DukeException("Error loading tasks from storage");
        }
    }

    /**
     * Saves tasks from the filepath.
     *
     * @return The list of tasks saved from the file.
     * @throws DukeException If there is an error saving tasks from storage.
     */
    public void save(List<Task> tasks) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("filePath"))) {
            output.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
