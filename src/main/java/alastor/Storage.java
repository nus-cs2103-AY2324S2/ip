package alastor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import alastor.task.Task;

/**
 * Represents a storage object that handles the loading and saving of tasks to a file.
 */
public class Storage {

    protected String filePath;
    protected File storageFile;

    /**
     * Constructs a storage object with the specified file path.
     *
     * @param filePath The file path of the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.storageFile = new File(filePath);
    }

    /**
     * Loads the tasks from the storage file.
     *
     * @return The list of tasks loaded from the storage file.
     * @throws AlastorException If an error occurs while loading the tasks from the storage file.
     */
    public ArrayList<Task> load() throws AlastorException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(this.storageFile);
            int index = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isBlank()) {
                    continue;
                }
                Parser.parseFile(line, list, index);
                index++;
            }
        } catch (FileNotFoundException e) {
            File parentDir = storageFile.getParentFile();
            if (!parentDir.exists()) {
                if (parentDir.mkdir()) {
                    Ui.dirCreated = true;
                } else {
                    throw new AlastorException("I'm afraid I've encountered an error while creating a directory for"
                            + " your tasks.\n");
                }
            }
            try {
                if (storageFile.createNewFile()) {
                    Ui.fileCreated = true;
                } else {
                    throw new AlastorException("I'm afraid the file I tried creating already exists.\n");
                }
            } catch (IOException newException) {
                throw new AlastorException("I'm afraid I've encountered an error while creating a file for your tasks."
                        + "\n");
            }
        }
        return list;
    }

    /**
     * Saves the specified task to the storage file.
     *
     * @param task The task to be saved to the storage file.
     * @throws AlastorException If an error occurs while saving the task to the storage file.
     */
    public void saveAdd(Task task) throws AlastorException {
        assert task != null : "Task should not be null";
        try {
            FileWriter writer = new FileWriter(this.filePath, true);
            writer.write(task.toFile() + "\n");
            writer.close();
        } catch (IOException e) {
            throw new AlastorException("I'm afraid I've encountered an error while saving your tasks.\n");
        }
    }

    /**
     * Rewrites the storage file with the specified list of tasks.
     *
     * @param tasks The list of tasks to be saved to the storage file.
     * @throws AlastorException If an error occurs while rewriting the storage file with the list of tasks.
     */
    public void saveRewrite(TaskList tasks) throws AlastorException {
        assert tasks != null : "TaskList should not be null";
        try {
            FileWriter writer = new FileWriter(this.filePath);
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).toFile() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new AlastorException("I'm afraid I've encountered an error while saving your tasks.\n");
        }
    }
}
