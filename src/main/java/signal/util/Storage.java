package signal.util;

import signal.task.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Manages the loading and saving of tasks to a file.
 * This class encapsulates file operations, allowing tasks to be persisted to disk and loaded back into the application.
 */
public class Storage {
    private static final String FILE_PATH = "../data/signal.txt";

    /**
     * Constructs Storage to the specified file path for saving and loading tasks.
     *
     */
    public Storage() {
    }

    /**
     * Loads tasks from the file specified by the path.
     * If the file does not exist, it creates a new file. Each line in the file is expected to represent a single task.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> loadTasks() {
        TaskList taskList = new TaskList();

        try {
            File file = new File(this.FILE_PATH);
            if (!file.exists()) {
                // Create the file and necessary directory structure if it doesn't exist
                file.getParentFile().mkdirs();
                file.createNewFile();
                return taskList.giveList(); // Return an empty list since there are no tasks yet
            }

            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;

            while ((line = reader.readLine()) != null) {
                // Parse each line and create Task objects
                Task task = parseTask(line);
                if (task != null) {
                    taskList.addTask(task);
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }

        return taskList.giveList();
    }

    /**
     * Helper function to read each line from the file.
     *
     * @param line The next line from the file.
     * @return The Task recorded by the line.
     */
    public static Task parseTask(String line) {
        // Parse each line to create Task objects
        // Example line format: "T | 1 | read book"
        String[] parts = line.split(" \\| ");
        if (parts.length >= 3) {
            boolean isDone = parts[1].equals("X");
            String description = parts[2];
            if (parts[0] == "T") { // todo
                return new ToDo(description);
            } else if (parts[0] == "D") { // deadline
                String deadline = parts[3];
                return new Deadline(description, deadline);
            } else if (parts[0] == "E") { // event
                String from = parts[3];
                String to = parts[4];
                return new Event(description, from, to);
            }

        } else {
            System.out.println("Invalid line format: " + line);
            return null;
        }
        return null;
    }

    /**
     * Writes the current list of tasks to the file specified by the path.
     * Converts each task to a string that is recorded in the file.
     *
     * @param taskList The list of tasks to be written to the file.
     */
    public void writeTasks(ArrayList<Task> taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.FILE_PATH));

            for (Task task : taskList) {
                // Write each task to a line in the file
                writer.write(task.toString());
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
