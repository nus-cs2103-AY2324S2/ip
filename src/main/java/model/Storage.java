package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Main storage class for running the Zero bot.
 * 
 * <p>Handles saves and writes to the save file, as well as keeping track of all tasks in a {@code TaskList}.
 */
public class Storage {
    private File saveFile;
    private TaskList tasks;

    /**
     * Initializes the {@code Storage} instance with the {@code TaskList} in the save file.
     * 
     * <p>Creates a new empty {@code TaskList} if the save file is not found, or an error occurs
     *  while loading the save file.
     * 
     * @param filePath The {@code String} path of the save file.
     * @throws IOException If an I/O error occurs.
     */
    public Storage(String filePath) throws IOException {
        saveFile = new File(filePath);

        // Attempt to load TaskList from save file
        if (saveFile.exists()) {
            try {
                FileInputStream fis = new FileInputStream(saveFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                tasks = (TaskList) ois.readObject();
                saveTaskList(); // Rewrite task data as readObject deletes save data
                ois.close();
                fis.close();
            } catch (IOException | ClassNotFoundException | ClassCastException e) {
                // Read failed due to corrupt data
                tasks = new TaskList();
            }
        } else {
            // Create the necessary directories and file
            saveFile.getParentFile().mkdirs();
            saveFile.createNewFile(); // Throws Exception if unable to create, terminate the program
            tasks = new TaskList();
        }
    }

    public TaskList getTaskList() {
        return tasks;
    }

    /**
     * Saves the current {@code TaskList} into the save file.
     * 
     * <p> Reopens and closes the ObjectOutputStream each time the method is called
     *  to override the file and update properly.
     * 
     * @throws IOException If an I/O error occurs.
     */
    public void saveTaskList() throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream(saveFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.flush();
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
