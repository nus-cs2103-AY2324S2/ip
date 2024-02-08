package seedu.duke;

import seedu.duke.task.Task;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

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
     * @return ArrayList<Task> containing the saved <code>TaskList</code>
     * @throws DukeException in the event of an Exception
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        // Load tasks from file
        // Solution below inspired by:
        // https://stackoverflow.com/questions/16111496/java-how-can-i-write-my-arraylist-to-a-file
        // -and-read-load-that-file-to-the
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
            list = (ArrayList<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            try {
                // make directory first
                new File("./data").mkdirs();
                // then make file
                // Solution below inspired by https://www.w3schools.com/java/java_files_create.asp
                File myObj = new File(filepath);
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException i) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            System.out.println("Could not load tasks from file: " + e.getMessage());
        }
        return list;
    }

    /**
     * Saves the modified TaskList into the application from the path provided.
     * @param list Contains the <code>TaskList</code> to be saved
     */
    public void saveList(ArrayList<Task> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("Could not save tasks to file: " + e.getMessage());
        }
    }
}
