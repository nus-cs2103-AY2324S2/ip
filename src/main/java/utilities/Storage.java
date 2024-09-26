package utilities;

import java.util.ArrayList;

import task.Task;
import task.TaskList;

/**
 * The storage which helps to access the save file.
 */
public class Storage {
    /**
     * The save file stored in the storage.
     */
    private SaveFile saveFile;

    /**
     * Storage class constructor.
     * @param fileName The file name of the save file.
     * @param directoryName The directory path of where the save file is saved.
     */
    public Storage(String fileName, String directoryName) {
        this.saveFile = new SaveFile(fileName, directoryName);
        this.saveFile.createDirectory();
        this.saveFile.createFile();
    }

    /**
     * Save task list to save file.
     * @param taskList The task list to be saved as a TaskList.
     */
    public void save(TaskList taskList) {
        this.saveFile.saveToFile(taskList);
    }

    /**
     * Load task list from save file.
     * @return The save file as an array list.
     */
    public ArrayList<Task> load() {
        return this.saveFile.readFile();
    }
}
