package util;

import exceptions.ChillChiefException;
import tasks.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Responsible for saving and loading tasks to and from a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage object.
     *
     * @param filepath The file path to the file where the loading and saving is done.
     */
    public Storage(String filepath) {
        this.filePath = filepath;
    }

    /**
     * Loads the tasks from the file.
     *
     * @return An Arraylist of tasks.
     * @throws IOException If parsing file contents results in an error.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (Files.exists(Paths.get(filePath))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        Task task = Parser.parseFileFormattedTask(line);
                        tasks.add(task);
                    } catch (ChillChiefException e) {
                        // Handle parse exceptions, e.g., log them or throw a custom exception
                        System.err.println("Error parsing task from file: " + e.getMessage());
                    }
                }
            }
        } else {
            System.out.println("No saved tasks found.");
        }
        return tasks;
    }

    /**
     * Saves the tasks to the file.
     *
     * @param tasks The tasks that are to be saved.
     * @throws ChillChiefException If there is an error saving tasks to the file.
     * @throws IOException If there is an error saving tasks to the file.
     */
    public void save(ArrayList<Task> tasks) throws ChillChiefException, IOException {
        Path path = Paths.get(this.filePath);
        // Ensure the parent directories exist
        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath, false))) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new ChillChiefException("Error saving tasks to file!: " + e.getMessage());
        }
    }

}