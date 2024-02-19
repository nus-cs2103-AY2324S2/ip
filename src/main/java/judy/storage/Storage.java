package judy.storage;
import judy.task.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Represents the file used to store tasks data.
 */
public class Storage {
    private final File file;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Checks if the file associated with this storage exists.
     *
     * @return {@code true} if the file exists, {@code false} otherwise.
     */
    public boolean isFileExists() {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            System.out.println("Error checking file existence: " + e);
            return false;
        }
    }

    /**
     * Creates a new file at the specified file path.
     */
    public void createNewFile() {
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to create new file");
        }
    }

    /**
     * Decodes and Loads tasks from the file.
     *
     * @return The list of tasks loaded and decoded from the file. If an error occurs during loading,
     *         an empty list is returned.
     */
    public ArrayList<Task> load() {
        if (file.length() == 0) {
            return new ArrayList<>(); // Handle empty file
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder encodedTasks = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                encodedTasks.append(line).append("\n");
            }

            ArrayList<Task> decodedTasks = Decoder.decodeTasks(encodedTasks.toString()).getTaskList();

            // Check if decoding resulted in null
            if (decodedTasks == null) {
                // Handle the null case (log, display an error, etc.)
                System.err.println("Error decoding tasks from file. Null result.");
                return new ArrayList<>();
            }

            return new ArrayList<>(decodedTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    /**
     * Encode the provided task list and updates the file.
     *
     * @param taskList The list of tasks to be encoded and written to the file.
     */
    public void save(ArrayList<Task> taskList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            String encodedTasks = Encoder.encodeTasks(taskList);
            writer.print(encodedTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
