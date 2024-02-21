package TaskFlow.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import TaskFlow.exception.DukeException;
import TaskFlow.task.Task;
import TaskFlow.task.TaskList;

/**
 * Represents the storage for tasks in the Duke chatbot application.
 * Handles loading tasks from a file and saving tasks to a file.
 */
public class Storage {

    private static final String DIRECTORY_PATH = "./data/";
    private String filePath;


    /**
     * Constructs a Storage object with the specified file path for storage.
     *
     * @param filePath The file path for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file.
     *
     * @return ArrayList of tasks loaded from the file.
     * @throws DukeException If an error occurs during the loading process.
     */
    public ArrayList<Task> load() throws DukeException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        // Create a new file if it doesn't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Error creating a new File: " + e.getMessage());
            }
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = Task.fromString(line);
                    tasks.add(task);
                }
            } catch (IOException e) {
                throw new DukeException("Error loading tasks: " + e.getMessage());
            }
        }
        return tasks;
    }

    /**
     * Saves the tasks in the provided TaskList to the specified file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws DukeException If an error occurs during the saving process.
     */
    public void saveTask(TaskList tasks) throws DukeException {
        File directory = new File(DIRECTORY_PATH);

        // Create a directory if it doesn't exist.
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Write the tasks in the list into the file.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            ArrayList<Task> taskList = tasks.getTasks();
            for (Task task : taskList) {
                writer.write(task.toString());
                writer.newLine();
            }
            System.out.println("Tasks saved successfully.");
        } catch (IOException e) {
            throw new DukeException("Error saving tasks to file: " + e.getMessage());
        }
    }

//    public void saveArchiveTask(ArrayList<Task> archiveTasks) throws DukeException {
//        File directory = new File(DIRECTORY_PATH);
//
//        // Create a directory if it doesn't exist.
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
//
//        // Write the tasks in the list into the file.
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//            ArrayList<Task> taskList = archiveTasks;
//            for (Task archiveTask : taskList) {
//                writer.write(archiveTask.toString());
//                writer.newLine();
//            }
//            System.out.println("Archive task saved successfully.");
//        } catch (IOException e) {
//            throw new DukeException("Error saving task to file: " + e.getMessage());
//        }
//    }
}
