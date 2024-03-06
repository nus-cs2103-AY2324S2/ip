package denify.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

import denify.exception.DenifyException;
import denify.task.Deadline;
import denify.task.Event;
import denify.task.Task;
import denify.task.Todo;

/**
 * Manages the loading and saving of tasks from/to a file in Denify,
 * interacting with the file system to store and retrieve tasks.
 */
public class Storage {
    protected Path filePath;
    /**
     * Indicates whether the warning about potential overwriting has been printed.
     */
    private boolean isWarningPrinted = false;
    /**
     * Constructs a `Storage` with the given file path.
     *
     * @param filePath The file path for storing tasks.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path must not be null";
        this.filePath = Paths.get(filePath);
    }
    /**
     * Prints a warning about potential overwriting of tasks.
     */
    private void printWarning() {
        if (!isWarningPrinted) {
            System.err.println("Warning: Invalid input will be ignored and overwritten after new task is added.\n"
                    + "To prevent overwriting, type 'bye' to quit.");
            isWarningPrinted = true;
        }
    }
    /**
     * Prepares the task file by creating directories and the file if they do not exist.
     *
     * @throws DenifyException If there is an issue preparing the file.
     */
    private void prepareFile() throws DenifyException {
        try {
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new DenifyException("Unable to create task file in " + this.filePath);
        }
    }
    /**
     * Loads tasks from the task file.
     *
     * @return The list of tasks loaded from the task file.
     * @throws DenifyException If there is an issue loading tasks.
     */
    public ArrayList<Task> loadTasks() throws DenifyException {
        prepareFile();
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner myScannerObj = new Scanner(filePath)) {
            int lineNumber = 1;
            while (myScannerObj.hasNext()) {
                String line = myScannerObj.nextLine();
                try {
                    Task t = this.load(line);
                    tasks.add(t);
                } catch (DenifyException e) {
                    printWarning();
                    System.err.println(lineNumber + ": " + e.getMessage());
                }
                lineNumber++;
            }
        } catch (IOException e) {
            throw new DenifyException("Unable to load tasks from task file.");
        }
        return tasks;
    }
    /**
     * Saves tasks to the task file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws DenifyException If there is an issue saving tasks.
     */
    public void saveTasks(ArrayList<Task> tasks) throws DenifyException {
        prepareFile();
        try {
            ArrayList<String> lines = new ArrayList<>();
            for (Task t : tasks) {
                lines.add(t.toFileString());
            }
            Files.write(filePath, lines, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new DenifyException("Unable to save tasks to task file.");
        }
    }
    /**
     * Loads a task from a string representation.
     *
     * @param line The string representation of the task.
     * @return The Task object created from the string representation.
     * @throws DenifyException If there is an issue loading the task.
     */
    public Task load(String line) throws DenifyException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3 || !parts[1].matches("[01]")) {
            throw new DenifyException("Invalid task format. Please check the task file.");
        }
        String taskType = parts[0];
        boolean isDone = Integer.parseInt(parts[1]) == 1;
        String description = parts[2];
        switch (taskType) {
        case "T":
            Task tT = new Todo(description);
            tT.setDone(isDone);
            return tT;
        case "D":
            Task tD = new Deadline(description, parts[3]);
            tD.setDone(isDone);
            return tD;
        case "E":
            String[] time = parts[3].split(" - ");
            if (!(time.length == 2)) {
                throw new DenifyException("Invalid time format. "
                        + "Please use <datetime> - <datetime>");
            }
            Task tE = new Event(description, time[0], time[1]);
            tE.setDone(isDone);
            return tE;
        default:
            throw new DenifyException("Invalid task type " + taskType);
        }
    }
}
