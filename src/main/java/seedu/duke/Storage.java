package seedu.duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import seedu.duke.task.Task;

/**
 * Represents <code>Storage</code> logic to save and load past
 * task lists from prior user input
 */
public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads the saved TaskList into the application from the path provided.
     * @return {@code ArrayList<Task>} containing the saved <code>TaskList</code>
     * @throws DukeException in the event of an Exception
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        // Load tasks from file
        // Solution below inspired by:
        // https://stackoverflow.com/questions/16111496/java-how-can-i-write-my-arraylist-to-a-file
        // -and-read-load-that-file-to-the
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
            // safe to cast because we know the object is an ArrayList<Task>
            taskList = (ArrayList<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            try {
                // make directory first
                new File("./data").mkdirs();
                // then make file
                // Solution below inspired by https://www.w3schools.com/java/java_files_create.asp
                File dataFile = new File(filepath);
                if (dataFile.createNewFile()) {
                    System.out.println("File created: " + dataFile.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException i) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return taskList;
    }

    /**
     * Saves the modified TaskList into the application from the path provided.
     * @param list Contains the <code>TaskList</code> to be saved
     */
    public void saveTasks(ArrayList<Task> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("Could not save tasks to file: " + e.getMessage());
        }
    }
}
