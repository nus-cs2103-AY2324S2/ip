package jerry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles storage operations for the chatbot application, including loading tasks from a file and saving tasks to a file.
 * This class abstracts away the details of file read/write operations, providing a simple interface for task persistence.
 */
public class Storage {

    String filePath;

    /**
     * Constructs a new Storage instance using the specified file path for task data.
     *
     * @param filePath The path to the file used for storing task data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the current list of tasks to the configured file.
     *
     * @param tasks The list of tasks to save.
     */
    public static void saveTasks(ArrayList<Task> tasks, String filePath) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Task task : tasks) {
                writer.println(task.toFileFormat());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving tasks to file.");
        }
    }

    /**
     * Loads tasks from the configured file into the application.
     *
     * @return An ArrayList containing the tasks loaded from the file.
     */
    public static ArrayList<Task> loadTasks(String filePath) {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks; // Return an empty list if file doesn't exist
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = parseTask(line); // Implement parsing logic
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading tasks from file.");
        }

        return tasks;
    }

    public static Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                return null; // Not enough parts for a valid task
            }

            String type = parts[0];
            boolean isDone = parts[1].trim().equals("1");
            String description = parts[2];

            switch (type) {
                case "T":
                    ToDo todo = new ToDo(description);
                    if (isDone) {
                        todo.markDone();
                    }
                    return todo;

                case "D":
                    if (parts.length < 4) return null; // Missing deadline date
                    Deadline deadline = new Deadline(description, parts[3]);
                    if (isDone) {
                        deadline.markDone();
                    }
                    return deadline;

                case "E":
                    if (parts.length < 5) {
                        System.out.println("event reading failed");
                        return null; // Missing event start and end times
                    }

                    Event event = new Event(description, parts[3], parts[4]);
                    if (isDone) {
                        event.markDone();
                    }
                    return event;

                default:
                    return null; // Unknown task type
            }
        } catch (Exception e) {
            System.out.println("Error parsing task from file: " + line);
            return null;
        }
    }
}
