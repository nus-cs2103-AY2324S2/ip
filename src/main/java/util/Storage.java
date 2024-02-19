package util;

import exceptions.DukeException;
import tasks.Task;
import util.Parser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Storage {
    private String filePath;

    public Storage(String filepath) {
        this.filePath = filepath;
    }

    // load returns a arraylist object, which can be used to make a tasklist object
    public ArrayList<Task> load() throws IOException, DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (Files.exists(Paths.get(filePath))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        Task task = Parser.parseFileFormattedTask(line);
                        tasks.add(task);
                    } catch (DukeException e) {
                        // Handle parse exceptions, e.g., log them or throw a custom exception
                        System.err.println("Error parsing task from file: " + e.getMessage());
                        // Optionally rethrow or continue processing remaining tasks
                        // throw e; // Uncomment to rethrow
                    }
                }
            }
        } else {
            // Handle the case where the file does not exist
            // This might simply mean no tasks have been saved yet, so just return the empty list
            // Or, if appropriate, throw an exception or log a message
            System.out.println("No saved tasks found.");
        }
        return tasks;
    }

    // takes in an arraylist, then saves it based on each task's overridden toFileString
    public void save(ArrayList<Task> tasks) throws DukeException, IOException {
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
            throw new DukeException("Error saving tasks to file!: " + e.getMessage());
        }
    }

}