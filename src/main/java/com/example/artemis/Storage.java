package com.example.artemis;

import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class Storage {
    private static String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

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
