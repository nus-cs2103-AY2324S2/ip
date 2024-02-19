package duke;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import duke.exceptions.ChatException;
import duke.task.Task;


/**
 * General functionality handling persistence of task lists.
 * Partial credits to
 * <a href="https://stackoverflow.com/questions/10654236/java-save-object-data-to-a-file">StackOverflow</a>
 * for some of the code here.
 */
public class Storage {
    private static final String fileName = "duke.ser";

    /**
     * Save tasks to file.
     *
     * @param tasks ArrayList of tasks.
     * @throws ChatException if we cannot write to the file.
     */
    public static void save(ArrayList<Task> tasks) {
        try (FileOutputStream file = new FileOutputStream(fileName);
             ObjectOutputStream objectOut = new ObjectOutputStream(file);) {
            for (Object task : tasks) {
                objectOut.writeObject(task);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
            throw new ChatException("Error writing duke logs file.");
        }
    }

    /**
     * Load tasks from a file.
     *
     * @return ArrayList of tasks
     * @throws ChatException if file cannot be read or loaded from.
     */
    public static ArrayList<Task> load() {
        try (FileInputStream file = new FileInputStream(fileName);
             ObjectInputStream objectIn = new ObjectInputStream(file);) {
            try {
                ArrayList<Task> result = new ArrayList<>();
                while (file.available() > 0) {
                    result.add((Task) objectIn.readObject());
                }
                return result;

            } catch (ClassNotFoundException e) {
                throw new ChatException("Error deserializing duke logs file.");
            }
        } catch (IOException e) {
            throw new ChatException("Error reading duke logs file:" + e.getMessage());
        }
    }
}
