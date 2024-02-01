/**
 * Represents a FileWriter object for Duke bot.
 * <p>
 * Handles writing of created Tasks information to the /resources directory, at data.txt.
 * Class is created to abstract the details of file input operations.
 */
package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import task.Task;
import task.TaskStorage;

public class DataWriter {
    private static final String SAVED_DATA_FILE = "data.txt";
    private static final String SAVED_DATA_DIRECTORY = "data";

    /**
     * Writes into the /resources/data.txt file line-by-line to save the created Tasks.
     * Note that it will override the text file, thus ensuring that it's updated to the latest copy.
     *
     * @return A boolean value on whether the writing is successful or not.
     */
    public boolean saveData(TaskStorage taskStorage) {
        String currentDirectory = System.getProperty("user.dir");
        java.nio.file.Path directory = java.nio.file.Paths.get(currentDirectory, SAVED_DATA_DIRECTORY);
        File f = new File(directory.toString());
        f.mkdir();
        java.nio.file.Path path = java.nio.file.Paths.get(currentDirectory, SAVED_DATA_DIRECTORY, SAVED_DATA_FILE);
        
        ArrayList<Task> taskList = taskStorage.sl;
        boolean isSuccessful = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString(), false))) {
            for (Task task : taskList) {
                writer.write(task.formatDataLine());
                writer.newLine();
            }
            isSuccessful = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }

}
