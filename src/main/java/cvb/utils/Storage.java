package cvb.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cvb.tasks.Task;
import cvb.tasks.TaskParser;

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
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = TaskParser.parse(line);
                taskList.add(task);
            }
            reader.close();
        } catch (IOException e) {
            // ignored as taskList will be returned empty
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
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : taskList) {
                writer.write(task.toFile());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            // ignored, write to file failed possibly due to insufficient permissions
        }
    }
}
