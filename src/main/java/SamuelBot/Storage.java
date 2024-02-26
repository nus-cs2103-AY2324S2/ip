package SamuelBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Storage class handles the loading and saving of tasks from/to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        createNewFile();
    }

    /**
     * Parses a task from a string representation.
     *
     * @param line The string representation of the task.
     * @return The parsed Task object.
     */
    private Task parseTaskFromString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length >= 3) {
            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1"); // Check if task is marked as done
            String description = parts[2].trim();
            switch (type) {
                case "T":
                    return new Todo(description);
                case "D":
                    if (parts.length >= 4) {
                        String by = parts[3].trim();
                        return new Deadline(description, by);
                    } else {
                        System.out.println("Incomplete input for deadline task.");
                    }
                    break;
                case "E":
                    if (parts.length >= 5) {
                        String from = parts[3].trim();
                        String to = parts[4].trim();
                        return new Event(description, from, to);
                    } else {
                        System.out.println("Incomplete input for event task.");
                    }
                    break;
                default:
                    System.out.println("Unknown task type: " + type);
                    break;
            }
        } else {
            System.out.println("Incomplete input: " + line);
        }
        return null;
    }

    /**
     * Loads tasks from the file specified in the filePath.
     *
     * @return The list of tasks loaded from the file.
     */
    public List<Task> loadTasksFromFile() {
        List<Task> tasks = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Parse each line and create Task objects
                Task task = parseTaskFromString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the file specified in the filePath.
     *
     * @param taskList The list of tasks to be saved.
     */
    public void saveTasksToFile(List<Task> taskList) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : taskList) {
                writer.write(task.toFileString() + "\n"); // Write task details to the file
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Creates a new file if it does not exist.
     */
    private void createNewFile() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating new file: " + e.getMessage());
        }
    }
}
