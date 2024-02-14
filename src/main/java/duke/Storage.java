package duke;

import java.io.*;

/**
 * Represent a Storage class that saves and loads information.
 */
public class Storage {
    String filepath;

    /**
     * Constructor for the Storage class.
     *
     * @param filepath The filepath to the file that contains recorded tasks.
     */
    public Storage(String filepath) {
        assert filepath != null : "filepath should not be null!";
        this.filepath = filepath;
    }

    /**
     * Load the taskList from the filePath.
     *
     * @return TaskList The TaskList object to be loaded from the filePath.
     */
    public TaskList load() {
        try {
            FileInputStream file = new FileInputStream(filepath);
            ObjectInputStream input = new ObjectInputStream(file);
            TaskList taskList = (TaskList) input.readObject();
            input.close();
            return taskList;
        } catch (IOException | ClassNotFoundException error) {
            // return empty TaskList on exceptions
            TaskList taskList = new TaskList();
            return taskList;
        }
    }

    /**
     * Save the taskList object into the filePath.
     *
     * @param taskList The TaskList object to be saved.
     */
    public void save(TaskList taskList) throws IOException {
        FileOutputStream file = new FileOutputStream(filepath);
        ObjectOutputStream output = new ObjectOutputStream(file);
        output.writeObject(taskList);
        output.close();
    }
}
