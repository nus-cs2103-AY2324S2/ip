package roland;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import task.Task;


/**
 * The Storage class handles the loading of tasks from and saving of tasks to a specified file path.
 * It is responsible for deserializing the task data from the file during application startup.
 *
 * @author wolffe88
 */

public class Storage {

    public final String filePath;

    /**
     * Constructs a new Storage object with the specified file path.
     *
     * @param filePath The file path where the task data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Loads the ArrayList of tasks from the specified file path using object deserialization.
     *
     * @return The ArrayList of tasks loaded from the file.
     * @throws RolandException If the file is empty or does not exist, indicating a fresh start.
     */
    public ArrayList<Task> load() throws RolandException {
        if (new File(filePath).length() != 0) {
            return deserializeArrayList(filePath);
        } else {
            throw new RolandException("Let's get started shall we?");
        }
    }

    /**
     * Deserializes the ArrayList of tasks from the specified file path.
     *
     * @param filePath The file path from which to deserialize the tasks.
     * @return The deserialized ArrayList of tasks.
     */
    @SuppressWarnings("unchecked")
    private static ArrayList<Task> deserializeArrayList(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (ArrayList<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public String getFilePath() {
        return this.filePath;
    }
}
