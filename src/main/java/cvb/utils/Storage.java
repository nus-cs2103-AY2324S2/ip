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
 * Provides methods for reading and writing task data to a file.
 * Interacts with the file system to perform read and write operations on the task data file.
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

    private ArrayList<Task> getTasks(BufferedReader reader) throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            Task task;
            try {
                task = TaskParser.parse(line);
            } catch (IllegalArgumentException e) {
                continue; // skip this line
            }
            taskList.add(task);
        }
        return taskList;
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
            taskList = getTasks(reader);
            reader.close();
        } catch (IOException e) {
            return taskList;
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
