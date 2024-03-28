package pyrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Handles saving and loading of task list.
 */
public class StateFile {
    private static final String FILE_PATH = "saves/state.txt";
    //  Solution below (use of ObjectOutputStream and FileOutputStream) inspired by ChatGPT
    private void saveObject(TaskList object) throws IOException {
        // Create directory if it does not exist
        File file = new File(FILE_PATH);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        FileOutputStream fileStream = new FileOutputStream(FILE_PATH);
        ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
        objectStream.writeObject(object);
        objectStream.close();
        fileStream.close();
    }
    private TaskList loadObject() throws IOException, ClassNotFoundException {
        FileInputStream fileStream = new FileInputStream(FILE_PATH);
        ObjectInputStream objectStream = new ObjectInputStream(fileStream);
        TaskList list;
        try {
            list = (TaskList) objectStream.readObject();
        } finally {
            objectStream.close();
            fileStream.close();
        }
        return list;
    }

    /**
     * Loads state from state file.
     * If state file does not exist, creates a new one.
     *
     * @param tasks TaskList to save if state file does not exist.
     * @return TaskList with loaded state.
     */
    public TaskList loadState(TaskList tasks) {
        assert tasks != null : "Default TaskList cannot be null.";
        try {
            TaskList loadedTasks = this.loadObject();
            return loadedTasks;
        } catch (IOException | ClassNotFoundException e) {
            // File issue, try to save blank state
            System.out.println("Unable to read saved state, creating new file...");
            this.saveState(tasks);
        }
        return tasks;
    }

    /**
     * Saves state to state file.
     *
     * @param tasks TaskList to save.
     * @return Error message if any.
     */
    public String saveState(TaskList tasks) {
        assert tasks != null : "TaskList to save cannot be null.";
        try {
            this.saveObject(tasks);
        } catch (IOException e) {
            return "Unable to save state: " + e.toString();
        }
        return "";
    }
}
