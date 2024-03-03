package dude.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import dude.tasks.Task;
import dude.tasks.TaskList;


/**
 * The Storage class handles the loading and saving of task data to a storage file.
 */
public class Storage {

    private final String filepath;
    private final String filename;

    /**
     * Constructor for the Storage class.
     *
     * @param fileLocation The file path to the storage file.
     */
    public Storage(String fileLocation) {
        assert (fileLocation != null);

        this.filepath = extractFilePath(fileLocation);
        this.filename = extractFileName(fileLocation);
    }

    /**
     * Creates a storage file if it does not exist.
     *
     * @throws IOException if an I/O error occurs
     * @throws SecurityException if a security manager exists and its checkWrite method denies write access to the file.
     */
    private void createStorageIfNotExists() throws IOException, SecurityException {
        File path = new File(this.filepath);

        //create the directory if it does not exist
        if (!path.exists()) {
            path.mkdirs();
        }

        File file = new File(this.filepath + this.filename);
        boolean fileExists = file.exists();
        //save an empty task list to the file if it does not exist
        if (!fileExists) {
            saveTasks(new TaskList());
        }
    }


    /**
     * Deletes the storage file if it exists.
     *
     * @throws SecurityException if a security manager exists and its checkWrite method denies write access to the file.
     */
    public void deleteStorage() throws SecurityException {
        File file = new File(this.filepath + this.filename);

        boolean fileExists = file.exists();

        if (fileExists) {
            file.delete();
        }
    }

    /**
     * Saves the task list to the storage file.
     *
     * @param taskList The task list to be saved.
     * @throws IOException       if an I/O error occurs
     * @throws SecurityException if a security manager exists and its checkWrite method denies write access to the file.
     */
    public void saveTasks(TaskList taskList) throws IOException, SecurityException {
        assert (taskList != null);
        try {
            FileOutputStream fos = new FileOutputStream(this.filepath + this.filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList.getList());
            oos.close();
            fos.close();
        } catch (IOException e) {
            throw new IOException("An error occurred while saving the task list.");
        }
    }

    /**
     * Loads the task list from the storage file. Returns empty task list if no task data is found.
     *
     * @return TaskList
     * @throws IOException If an I/O error occurs.
     * @throws ClassNotFoundException If the class of the object to be loaded was not found.
     * @throws SecurityException If a security manager exists and its checkRead method denies read access to the file.
     */
    public TaskList loadTasks() throws IOException, ClassNotFoundException, SecurityException {
        createStorageIfNotExists();

        ArrayList<Task> list;
        try {
            FileInputStream fis = new FileInputStream(this.filepath + this.filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            //noinspection unchecked
            list = (ArrayList<Task>) ois.readObject();
            ois.close();
            fis.close();

            return TaskList.from(list);
        } catch (IOException e) {
            throw new IOException("An error occurred while loading the task list.");
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("The class of the object to be loaded was not found.");
        }
    }

    private String extractFilePath(String fileLocation) {
        String f = fileLocation.trim();
        String[] parts = f.split("/");
        String path = "";
        for (int i = 0; i < parts.length - 1; i++) {
            path += parts[i] + "/";
        }
        return path;
    }

    private String extractFileName(String fileLocation) {
        String f = fileLocation.trim();
        String[] parts = f.split("/");
        return parts[parts.length - 1];
    }
}
