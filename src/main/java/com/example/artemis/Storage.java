package com.example.artemis;

import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * Handles the loading and saving of tasks to a file for the Artemis application.
 */
public class Storage {
    // Filepath for storing tasks data
    private static String filepath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filepath The file path for storing tasks data.
     */
    public Storage(String filepath) {
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
                    throw new ArtemisException("Error loading task from file: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist, handle this case by creating an empty task list
        } catch (IOException e) {
            throw new ArtemisException("Error reading tasks from file: " + e.getMessage());
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
        try {
            createDirectoryIfNotExists("./data/");

            try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
                for (Task task : tasks) {
                    writer.println(task.toFileString());
                }
            }
        } catch (IOException e) {
            throw new ArtemisException("Error saving tasks to file: " + e.getMessage());
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
                System.err.println("Failed to create the directory: " + directoryPath);
            }
        }
    }
}
