package campus.infrastructure;

import java.nio.file.Files;
import java.io.BufferedWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

import campus.tasks.Task;


/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final String filePath;
    private List<String> listOfStrings;

    /**
     * Initialises the Storage Class given a specific location if the file exists.
     * @param filePath The filePath as a String of the textfile used for storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;

        try {
            this.listOfStrings = this.readFromDBCreateIfNotExists(this.filePath);
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialises the Storage Class given a specific location if the file exists.
     * @param filePath The filePath as a String of the textfile used for storage.
     * @param newDatabase Boolean parameter as to whether you want to erase contents
     */
    public Storage(String filePath, Boolean newDatabase) {
        if (newDatabase) {
            this.filePath = filePath;

            // Delete existing file
            File fileToDelete = new File(filePath);
            if (fileToDelete.exists()) {
                if (!fileToDelete.delete()) {
                    System.err.println("Error: Unable to delete existing file at " + filePath);
                }
            }

            // Create new file
            try {
                if (!fileToDelete.createNewFile()) {
                    System.err.println("Error: Unable to create new file at " + filePath);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Initialize listOfStrings
            this.listOfStrings = new ArrayList<>();
        } else {
            this.filePath = filePath;

            try {
                this.listOfStrings = this.readFromDBCreateIfNotExists(this.filePath);
            } catch (FileNotFoundException e) {
                System.err.println("Error: " + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getListOfStrings() {
        return this.listOfStrings;
    }

    /**
     * Function to read from the expected file called "data", if not, create the file and return null.
     * @param filePath Takes in the relative path file that it expects the datafile to be at.
     * @return Returns a list of strings, each string contains information about the DB, +
     *      null if initialised for the first time.
     */
    public List<String> readFromDBCreateIfNotExists(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createFile(path);
            return null;
        }
        return Files.readAllLines(path);
    }

    /**
     * Updates the taskList object given the inputs in the file.
     * @param taskList the taskList Object.
     */
    public void updateFileFromList(TaskList taskList) {
        String filePath = this.filePath;
        assert (filePath != null);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            List<Task> listOfTasks = taskList.getListOfTasks();
            for (Task task : listOfTasks) {
                writer.write(task.toDBFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
