package cleo;

import java.io.*;
import java.util.ArrayList;

/**
 * Provides functionality for saving and loading task data to and from a file.
 * This class acts as a persistent storage mechanism for tasks.
 */
public class TaskStorage {
    /**
     * The default file path for storing task data.
     */
    private static final String FILE_PATH = "../data/duke.txt";

    /**
     * Saves an ArrayList of tasks to a file. Ensures necessary directories exist and handles potential exceptions.
     *
     * @param tasks The ArrayList of Task objects to be saved
     * @throws DukeException if there is an error during the save operation
     */
    public static void saveTasks(ArrayList<Task> tasks) throws DukeException {
        try {
            File file = new File(FILE_PATH);
            File directory = file.getParentFile();
            if (!directory.exists()) {
                directory.mkdirs(); // Create the directory if it doesn't exist
            }

            if (!file.exists()) {
                file.createNewFile(); // Create the file if it doesn't exist
            }

            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(tasks);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from a file. Handles cases where the file doesn't exist  or if an issue occurs during reading.
     *
     * @return An ArrayList containing the loaded tasks (empty if no file or corruption occurs)
     * @throws DukeException if there's an error during the loading process
     */

    public static ArrayList<Task> loadTasks() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks; // File not found, return empty list
        }

        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            tasks = (ArrayList<Task>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("No saved tasks found. Starting a new task list.");
        } catch (InvalidClassException | ClassNotFoundException e) {
            throw new DukeException("duke.Task data is corrupted or in an incompatible format.");
        } catch (IOException e) {
            throw new DukeException("Error occurred while reading the file: " + e.getMessage());
        }

        return tasks;
    }
}