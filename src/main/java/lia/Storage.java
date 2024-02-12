package lia;

import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * The Storage class handles the loading and saving of tasks from/to a file.
 * It interacts with the TaskList and various task-related classes for file I/O operations.
 */
public class Storage {
    private static final String FILE_NAME = "tasks.txt";
    private static final String FOLDER_NAME = "data";
    private static final String PROJECT_FOLDER = ".";
    private static final Path FILE_PATH = Paths.get(PROJECT_FOLDER, FOLDER_NAME, FILE_NAME);

    /**
     * Constructs a Storage object, ensuring the existence of necessary directories.
     * It creates the required directory structure for storing task data.
     */
    public Storage() {
        try {
            Files.createDirectories(FILE_PATH.getParent());
        } catch (IOException e) {
            System.out.println("Error creating directories: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from a file and returns them as an ArrayList.
     *
     * @return An ArrayList containing the loaded tasks.
     * @throws LiaException If an error occurs during the loading process.
     */
    public ArrayList<Task> loadTasks() throws LiaException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Files.createDirectories(FILE_PATH.getParent());
            try (BufferedReader br = Files.newBufferedReader(FILE_PATH)) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];

                    switch (type) {
                    case "T":
                        tasks.add(new Todo(description, isDone));
                        break;
                    case "D":
                        String by = parts[3];
                        tasks.add(new Deadline(description, by, isDone));
                        break;
                    case "E":
                        String start = parts[3];
                        String end = parts[4];
                        tasks.add(new Event(description, start, end, isDone));
                        break;
                    default:
                        System.out.println("Unknown task type: " + type);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new LiaException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves tasks to a file based on their types and properties.
     *
     * @param tasks The ArrayList of tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            Files.createDirectories(FILE_PATH.getParent());
            try (BufferedWriter bw = Files.newBufferedWriter(FILE_PATH)) {
                for (Task task : tasks) {
                    String taskSave;
                    if (task instanceof Todo) {
                        taskSave = "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
                    } else if (task instanceof Deadline) {
                        taskSave = "D | " + (task.isDone() ? "1" : "0") + " | "
                                + task.getDescription() + " | " + ((Deadline) task).getDate();
                    } else if (task instanceof Event) {
                        taskSave = "E | " + (task.isDone() ? "1" : "0") + " | "
                                + task.getDescription() + " | " + ((Event) task).getStart()
                                + " | " + ((Event) task).getEnd();
                    } else {
                        continue;
                    }
                    bw.write(taskSave + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing tasks: " + e.getMessage());
        }
    }
}