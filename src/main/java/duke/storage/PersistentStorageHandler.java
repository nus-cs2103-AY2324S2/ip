package duke.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import duke.commons.exceptions.DukeException;
import duke.commons.exceptions.TaskDataNotFoundException;
import duke.task.TaskList;

/**
 * Handles persistent storage operations for Duke application, including reading
 * from and writing tasks to disk.
 * Supports operations such as checking for the existence of the storage file,
 * creating it if necessary, reading tasks from it, and saving current tasks
 * back to it.
 */
public class PersistentStorageHandler {

    private static final String TASKLIST_PATH = "./tasklist.dat";

    /**
     * Ensures the existence of the task file and initializes it if not present.
     * 
     * @return True if the file exists or was successfully created, false if the
     *         file did not exist and was created.
     * @throws DukeException If creating the file fails.
     */
    public static boolean taskFileFound() throws DukeException {
        File file = new File(TASKLIST_PATH);

        if (!file.exists()) {
            try {
                file.createNewFile();
                return false;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw new DukeException("Failed to create persistent task file.");
            }
        }
        return true;
    }

    /**
     * Decodes the task list from an ObjectInputStream.
     * 
     * @param ois The ObjectInputStream to read the task list from.
     * @return The decoded task list.
     * @throws DukeException If an error occurs during decoding.
     */
    private static TaskList decodeObjectInputStream(ObjectInputStream ois) throws DukeException {
        try {
            TaskList taskList = (TaskList) ois.readObject();
            return taskList;
        } catch (Exception e) { // todo: look into what exceptions can occur here
            throw new DukeException("Failed to decode: " + TASKLIST_PATH);
        }
    }

    /**
     * Reads the task list from disk.
     * 
     * @return The task list loaded from the file.
     * @throws DukeException If the file does not exist or an error occurs during
     *                       reading.
     */
    public static TaskList readTaskFileFromDisc() throws DukeException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TASKLIST_PATH));
            return decodeObjectInputStream(ois);
        } catch (Exception e) {
            throw new TaskDataNotFoundException(
                    "File: " + TASKLIST_PATH + " not found.\nWelcome to your new productivity journey.");
        }
    }

    /**
     * Writes the current task list to disk.
     * 
     * @param taskList The task list to be written to disk.
     * @throws DukeException If an error occurs during writing.
     */
    public static void writeTaskFileToDisc(TaskList taskList) throws DukeException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TASKLIST_PATH));
            oos.writeObject(taskList);
            oos.close();
        } catch (Exception e) {
            System.out.println(">>: " + e.getMessage());
            throw new DukeException("Failed to write to file: " + TASKLIST_PATH);
        }
    }
}
