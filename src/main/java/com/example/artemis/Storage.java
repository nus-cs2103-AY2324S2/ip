package com.example.artemis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 * Handles the loading and saving of tasks to a file for the Artemis application.
 */
public class Storage {
    public static final String ERROR_LOADING_TASK_FROM_FILE = "Error loading task from file: ";
    public static final String FILE_NOT_FOUND_FROM_THE_DIRECTORY = "File not found from the directory: ";
    public static final String ERROR_READING_TASKS_FROM_FILE = "Error reading tasks from file: ";
    public static final String ERROR_SAVING_TASKS_TO_FILE = "Error saving tasks to file: ";
    public static final String FAILED_TO_CREATE_THE_DIRECTORY = "Failed to create the directory: ";
    // Filepath for storing tasks data
    private static String filepath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filepath The file path for storing tasks data.
     */
    public Storage(String filepath) {
        assert filepath != null && !filepath.isEmpty() : "Filepath cannot be null or empty";

        this.filepath = filepath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws ArtemisException If there is an issue loading tasks from the file.
     */
    public ArrayList<Task> load() throws ArtemisException {
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Task task = Task.fromFileString(line);
                    tasks.add(task);
                } catch (IllegalArgumentException e) {
                    throw new ArtemisException(ERROR_LOADING_TASK_FROM_FILE + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            throw new ArtemisException(FILE_NOT_FOUND_FROM_THE_DIRECTORY + e.getMessage());
        } catch (IOException e) {
            throw new ArtemisException(ERROR_READING_TASKS_FROM_FILE + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks The ArrayList of Task objects to be saved to the file.
     * @throws ArtemisException If there is an issue saving tasks to the file.
     */
    public void save(ArrayList<Task> tasks) throws ArtemisException {
        assert tasks != null : "Tasks list cannot be null";

        try {
            createDirectoryIfNotExists("./data/");

            try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
                for (Task task : tasks) {
                    writer.println(task.toFileString());
                }
            }
        } catch (IOException e) {
            throw new ArtemisException(ERROR_SAVING_TASKS_TO_FILE + e.getMessage());
        }
    }

    /**
     * Creates a directory if it does not exist.
     *
     * @param directoryPath The path of the directory to be created.
     */
    private static void createDirectoryIfNotExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                System.err.println(FAILED_TO_CREATE_THE_DIRECTORY + directoryPath);
            }
        }
    }
}
