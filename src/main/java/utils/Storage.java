package utils;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

import tasks.Task;

/**
 * The {@code Storage} class provides methods for reading and writing task data to a file.
 * It interacts with the file system to perform read and write operations on the task data file.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a new {@code Storage} instance with the specified file path.
     *
     * @param filePath the file path where task data is stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads task data from the specified file and returns an {@code ArrayList} of tasks.
     *
     * @return an {@code ArrayList} of tasks read from the file
     */
    public ArrayList<Task> read() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Files.createDirectories(Paths.get(filePath).getParent());
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Parser.parseTaskFromLine(line);
                taskList.add(task);
            }
            reader.close();
        } catch (IOException e) {
            // Handle IOException (e.g., file not found, etc.)
        }
        return taskList;
    }

    /**
     * Writes the provided {@code ArrayList} of tasks to the specified file.
     *
     * @param taskList the {@code ArrayList} of tasks to be written to the file
     */
    public void write(ArrayList<Task> taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : taskList) {
                writer.write(task.toFile());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            // Handle IOException (e.g., file not found, etc.)
        }
    }
}
