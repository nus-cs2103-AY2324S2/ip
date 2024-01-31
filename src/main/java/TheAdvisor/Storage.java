package theadvisor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * A storage class to save and load tasklist that was instantiated by user before.
 */
public class Storage implements Serializable {
    private String filePath;

    /**
     * Creates a Storage object that takes in the path to save or load the TaskList from.
     *
     * @param filePath The path where the TaskList should be saved at.
     */
    public Storage (String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the list of Tasks from previous uses.
     * If it is the first instance of use, creates a new TaskList instead.
     *
     * @return A TaskList that is either loaded from previous use, or newly instantiated.
     * @throws IOException If unable to load file from the path.
     * @throws ClassNotFoundException If class definition has been changed.
     */
    public TaskList loadList() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filePath);
        ObjectInputStream out = new ObjectInputStream(fileIn);
        @SuppressWarnings("unchecked")
        TaskList task = (TaskList) out.readObject();
        out.close();
        fileIn.close();
        return task;
    }

    /**
     * Saves the task into the TaskList.
     *
     * @param taskList The TaskList to be saved.
     */
    public void saveTasks(TaskList taskList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(taskList);
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
            e.printStackTrace();
        }
    }
}