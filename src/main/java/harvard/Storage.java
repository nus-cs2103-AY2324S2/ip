package harvard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Storage class is responsible for handling the storage of tasks in the Harvard application.
 * It provides methods to load tasks from and store tasks to a text file.
 */
public class Storage {
    /**
     * The file path for storing tasks.
     */
    private String filePath;

    public Storage(String fP) {
        this.filePath = fP;
    }

    /**
     * Loads tasks from the stored text file and returns a BufferedReader.
     * If the file is not found, it creates a new text file.
     *
     * @return The BufferedReader containing task information.
     */
    public BufferedReader load() {
        try {
            BufferedReader buffReader = new BufferedReader(
                    new FileReader(System.getProperty("user.dir") + "/data/harvard.txt"));
            return buffReader;
        } catch (FileNotFoundException ex) {
            createTextFile();
        }
        return null;
    }

    /**
     * Creates a new text file for storing tasks.
     */
    public void createTextFile() {
        try {
            File file = new File(System.getProperty("user.dir") + "/data/harvard.txt");
            if (file.getParentFile().mkdir()) {
                file.createNewFile();
            } else {
                throw new IOException("Failed to create directory " + file.getParent());
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    /**
     * Stores the tasks from the TaskList to the text file.
     *
     * @param tasks The TaskList containing tasks to be stored.
     */
    public void store(TaskList tasks) {
        String textToSave = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            textToSave += tasks.getTask(i).saveString() + "\n";
        }
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(System.getProperty("user.dir")
                            + "/data/harvard.txt"));
            writer.write(textToSave);
            writer.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
