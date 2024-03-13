package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Manages storage operations for Duke application tasks.
 * <p>
 * This class handles the loading, adding, and saving of task objects to a file.
 * It maintains an in-memory list of tasks for quick access and manipulation.
 * </p>
 * <p>
 * The storage is implemented using a file-based approach, where tasks are written
 * to and read from a specified file. The file is stored in a directory named "data"
 * within the project's root directory.
 * </p>
 */
public class Storage {
    // Constants for file and directory names
    private static final String FILE_NAME = "rah.txt";
    private static final Path PROJECT_DIR = Paths.get(System.getProperty("user.dir"));
    private static final Path DATA_DIR = Paths.get(PROJECT_DIR.toString(), "data");
    private static final Path FILE_PATH = Paths.get(DATA_DIR.toString(), FILE_NAME);

    // In-memory list of tasks
    private static ArrayList<Task> tasks;

    /**
     * Constructs a new Storage instance and loads tasks from the file.
     */
    public Storage() {
        tasks = new ArrayList<>();
        loadDataFromFile();
    }


    /**
     * Loads tasks from the file into the in-memory list.
     * Handles exceptions such as IOException and DukeException.
     */
    public void loadDataFromFile() {
        try {
            File file = FILE_PATH.toFile();
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String taskData = scanner.nextLine();
                    Task task = createTaskFromData(taskData);
                    tasks.add(task);
                }
                scanner.close();
            }
        } catch (IOException | DukeException e) {
            // Handle exceptions as needed
            System.err.println("Error creating task: " + e.getMessage());
        }
    }

    /**
      * Creates a Task object based on the provided task data.
      * @param taskData The string representation of the task.
      * @return The Task object created from the task data.
      * @throws DukeException If an error occurs during task creation.
      */
    public Task createTaskFromData(String taskData) throws DukeException {
        String taskType = taskData.substring(0, 3);
        String taskStatus = taskData.substring(4, 5);
        switch (taskType) {
        case "[T]":
            return createToDoTask(taskData, taskStatus);
        case "[D]":
            return createDeadlinesTask(taskData, taskStatus);
        case "[E]":
            return createEventsTask(taskData, taskStatus);
        default:
            throw new DukeException("Unknown task type: " + taskType);
        }
    }

    private ToDo createToDoTask(String taskData, String taskStatus) {
        String description = taskData.substring(7);
        boolean isCompleted = taskStatus.equals("X");
        return new ToDo(description, isCompleted);
    }

    private Deadlines createDeadlinesTask(String taskData, String taskStatus) throws DukeException {
        String[] token = taskData.substring(7).split("\\(");
        if (token.length < 2) {
            throw new DukeException("Invalid task data format: " + taskData);
        }
        String description = token[0].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm'hrs'");
        String dateInput = token[1].substring(4, 22);
        LocalDateTime localDateTime = LocalDateTime.parse(dateInput, formatter);
        boolean isCompleted = taskStatus.equals("X");
        return new Deadlines(description, isCompleted, localDateTime);
    }

    private Events createEventsTask(String taskData, String taskStatus) throws DukeException {
        String[] token = taskData.substring(7).split("\\(");
        if (token.length < 2) {
            throw new DukeException("Invalid task data format: " + taskData);
        }
        String description = token[0].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm'hrs'");
        String input = token[1];
        String date1 = input.substring(6, 24);
        String date2 = input.substring(30, 48);
        System.out.println("DATE 1: " + date1);
        System.out.println("DATE 2: " + date2);
        LocalDateTime dateTime1 = LocalDateTime.parse(date1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(date2, formatter);
        boolean isCompleted = taskStatus.equals("X");
        return new Events(description, isCompleted, dateTime1, dateTime2);
    }

    /**
     * Writes the given list of tasks to the file specified in {@code FILE_PATH}.
     * Overwrites the existing content of the file.
     *
     * @param inventory The list of tasks to be written to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void overWriteToFile(ArrayList<Task> inventory) throws IOException {
        File file = FILE_PATH.toFile();
        // Ensure the directory exists
        file.getParentFile().mkdirs();

        try (FileWriter fw = new FileWriter(file, false)) { // false to overwrite
            for (Task task : inventory) {
                fw.write(task.toString() + System.lineSeparator());
            }
        }
    }

    /**
     * Adds a task to the in-memory list of tasks.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Retrieves the in-memory list of tasks.
     *
     * @return An ArrayList containing the tasks stored in memory.
     */
    public ArrayList<Task> load() {
        return tasks;
    }

    /**
     * Returns a string representation of the tasks stored in the in-memory list.
     *
     * @return A formatted string listing the tasks.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int count = 1;
        for (Task s : tasks) {
            result.append(count).append(". ").append(s.toString()).append("\n");
            count++;
        }
        return result.toString();
    }
}
