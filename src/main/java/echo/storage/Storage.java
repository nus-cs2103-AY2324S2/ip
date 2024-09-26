/*
 * Package: Echo.Storage
 * Module/Project Name: Echo
 * File: Storage.java
 *
 * Description:
 * This class manages the reading and writing of tasks to a file, providing persistence for the task data.
 *
 */

package echo.storage;

import echo.task.Task;
import echo.task.Todo;
import echo.task.Deadline;
import echo.task.Event;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
            String[] tokens = fileLine.split(" \\|");
            if (tokens.length <= 1) {
                throw new IllegalArgumentException("Invalid task format in file!");
            }

            String taskType = tokens[0];
            boolean isDone = tokens[1].equals(" 1");
            String taskDescription = tokens[2].trim();

            Task newTask;

            switch (taskType) {
            case "T":
                newTask = new Todo(taskDescription);
                break;
            case "D":
                newTask = createDeadlineTask(taskDescription, tokens[3], isDone);
                break;
            case "E":
                newTask = createEventTask(taskDescription, tokens[3], isDone);
                break;
            default:
                throw new IllegalArgumentException("Invalid task type in file!");
            }

            tasks.add(newTask);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a new Deadline task based on the provided description, deadline token, and completion status.
     *
     * @param description   The description of the task.
     * @param deadlineToken The token containing deadline information.
     * @param isDone        A boolean indicating whether the task is marked as done.
     * @return A new Deadline task.
     * @throws IllegalArgumentException If the deadline format in the file is invalid.
     */
    private Deadline createDeadlineTask(String description, String deadlineToken, boolean isDone) {
        String[] deadlineTokens = deadlineToken.split(" ", 2);
        if (deadlineTokens.length != 2) {
            throw new IllegalArgumentException("Invalid deadline format in file.");
        }
        Deadline deadlineTask = new Deadline(description, deadlineTokens[1]);
        if (isDone) {
            deadlineTask.markAsDone();
        }
        return deadlineTask;
    }


    /**
     * Creates a new Event task based on the provided description, event token, and completion status.
     *
     * @param description The description of the task.
     * @param eventToken  The token containing event information.
     * @param isDone      A boolean indicating whether the task is marked as done.
     * @return A new Event task.
     * @throws IllegalArgumentException If the event format in the file is invalid.
     */
    private Event createEventTask(String description, String eventToken, boolean isDone) {
        String[] eventTokens = eventToken.split(" ", 3);
        if (eventTokens.length != 3) {
            throw new IllegalArgumentException("Invalid event format in file.");
        }
        Event eventTask = new Event(description, eventTokens[1], eventTokens[2]);
        if (isDone) {
            eventTask.markAsDone();
        }
        return eventTask;
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

    /**
     * Handles the case where the tasks file is missing. Creates a new file if it doesn't exist.
     *
     * @param file The tasks file.
     * @throws IOException If an I/O error occurs while creating the file.
     */
    private void handleMissingFile(File file) throws IOException {
        file.getParentFile().mkdirs(); // Create parent directories if they don't exist
        file.createNewFile(); // Create the file if it doesn't exist
        System.out.println("No tasks file found. Created a new tasks file.");
    }

    /**
     * Reads tasks from the specified file and populates the provided list of tasks.
     *
     * @param file        The tasks file to read.
     * @param loadedTasks The list to populate with loaded tasks.
     * @throws IOException If an I/O error occurs while reading the file.
     */
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


    /**
     * Handles an error that occurs during tasks file loading. Deletes the corrupted file and creates a new tasks file.
     *
     * @param e           The IOException that occurred during file loading.
     * @param loadedTasks The list of loaded tasks.
     */
    private void handleFileLoadError(IOException e, List<Task> loadedTasks) {
        System.out.println("Error loading tasks from file: " + e.getMessage());

        // Handle the situation where the file is corrupted
        System.out.println("Deleting the corrupted file and creating a new tasks file.");
        File corruptedFile = new File(FILE_PATH);
        corruptedFile.delete(); // Delete the corrupted file
        loadedTasks.addAll(load()); // Recursively call the method to create a new file
    }

}
