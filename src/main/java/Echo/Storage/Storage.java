/*
 * Package: Echo.Storage
 * Module/Project Name: Echo
 * File: Storage.java
 *
 * Description:
 * This class manages the reading and writing of tasks to a file, providing persistence for the task data.
 *
 */

package Echo.Storage;

import Echo.Task.Task;
import Echo.Task.Todo;
import Echo.Task.Deadline;
import Echo.Task.Event;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String FILE_PATH;

    /**
     * Constructor for the Storage class.
     *
     * @param filePath The path to the file used for storing tasks.
     */
    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * Parses a line from the file and adds the corresponding task to the provided list of tasks.
     *
     * @param fileLine The line read from the file.
     * @param tasks    The list of tasks to which the parsed task will be added.
     */
    private void addTaskFromFileString(String fileLine, List<Task> tasks) {
        try {
            // Parse the file line into task information
            String[] tokens = fileLine.split(" \\|");
            if (tokens.length <= 1) {
                throw new IllegalArgumentException("Invalid task format in file!");
            }

            // Extract task type, status, and description
            String taskType = tokens[0];
            System.out.println(tokens[1]);
            boolean isDone = tokens[1].equals(" 1");
            System.out.println(isDone);
            if (tokens[2].isEmpty()) {
                throw new IllegalArgumentException("The description of a task cannot be empty.");
            }
            String taskDescription = tokens[2].trim();

            // Create the corresponding task object based on the task type
            switch (taskType) {
            case "T":
                Todo todoTask = new Todo(taskDescription);
                if (isDone) {
                    todoTask.markAsDone();
                }
                tasks.add(todoTask);
                break;
            case "D":
                String[] deadlineTokens = tokens[3].split(" ", 2);
                if (deadlineTokens.length != 2) {
                    throw new IllegalArgumentException("Invalid deadline format in file.");
                }
                Deadline deadlineTask = new Deadline(taskDescription, deadlineTokens[1]);
                if (isDone) {
                    deadlineTask.markAsDone();
                }
                tasks.add(deadlineTask);
                break;
            case "E":
                String[] eventTokens = tokens[3].split(" ", 3);
                if (eventTokens.length != 3) {
                    throw new IllegalArgumentException("Invalid event format in file.");
                }
                Event eventTask = new Event(taskDescription, eventTokens[1], eventTokens[2]);
                if (isDone) {
                    eventTask.markAsDone();
                }
                tasks.add(eventTask);
                break;
            default:
                throw new IllegalArgumentException("Invalid task type in file!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void save(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Write each task to the file
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file and returns them as a list.
     *
     * @return The list of tasks loaded from the file.
     */
    public List<Task> load() {
        List<Task> loadedTasks = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                handleMissingFile(file);
            } else {
                readTasksFromFile(file, loadedTasks);
            }

        } catch (IOException e) {
            handleFileLoadError(e, loadedTasks);
        }

        return loadedTasks;
    }

    private void handleMissingFile(File file) throws IOException {
        file.getParentFile().mkdirs(); // Create parent directories if they don't exist
        file.createNewFile(); // Create the file if it doesn't exist
        System.out.println("No tasks file found. Created a new tasks file.");
    }

    private void readTasksFromFile(File file, List<Task> loadedTasks) throws IOException {
        // Read each line from the file and attempt to add the corresponding task
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    addTaskFromFileString(line, loadedTasks);
                } catch (IllegalArgumentException e) {
                    // Handle invalid task lines gracefully
                    System.out.println("Error loading a task. Skipping invalid task line: " + line);
                }
            }
        }
    }

    private void handleFileLoadError(IOException e, List<Task> loadedTasks) {
        System.out.println("Error loading tasks from file: " + e.getMessage());

        // Handle the situation where the file is corrupted
        System.out.println("Deleting the corrupted file and creating a new tasks file.");
        File corruptedFile = new File(FILE_PATH);
        corruptedFile.delete(); // Delete the corrupted file
        loadedTasks.addAll(load()); // Recursively call the method to create a new file
    }

}
