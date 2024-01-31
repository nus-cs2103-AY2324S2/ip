package huyang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Storage class responsible for loading and saving tasks from/to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for the Storage class.
     *
     * @param filePath The file path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file and returns them as an ArrayList.
     *
     * @return An ArrayList of loaded tasks.
     * @throws IOException     If an I/O error occurs while reading the file.
     * @throws TaskException  If there is an issue parsing the file content into tasks.
     */
    public ArrayList<Task> loadTasks() throws IOException, TaskException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(parseLineToTask(line));
            }
        }
        return tasks;
    }

    /**
     * Saves tasks to the storage file.
     *
     * @param tasks An ArrayList of tasks to be saved.
     * @throws IOException     If an I/O error occurs while writing to the file.
     * @throws TaskException  If there is an issue formatting tasks for saving.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException, TaskException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                bw.write(task.toFileFormat());
                bw.newLine();
            }
        }
    }

    /**
     * Parses a line of text from the storage file into a Task object.
     *
     * @param line A line of text from the storage file.
     * @return A Task object representing the parsed line.
     * @throws TaskException If there is an issue parsing the line into a Task object.
     */
    private Task parseLineToTask(String line) throws TaskException {
        String type = line.substring(0, 1);
        switch (type) {
        case "T":
            return ToDo.fromFileFormat(line);
        case "D":
            return Deadline.fromFileFormat(line);
        case "E":
            return Event.fromFileFormat(line);
        default:
            throw new TaskException("Unknown task type in file: " + type);
        }
    }
}
