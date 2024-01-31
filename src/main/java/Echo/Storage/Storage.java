package Echo.Storage;

import Echo.Task.Task;
import Echo.Task.Todo;
import Echo.Task.Deadline;
import Echo.Task.Event;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String FILE_PATH;

    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }
    private void addTaskFromFileString(String fileLine, List<Task> tasks) {
        try {
            String[] tokens = fileLine.split(" \\|");
            if (tokens.length <= 1) {
                throw new IllegalArgumentException("Invalid task format in file!");
            }

            String taskType = tokens[0];
            if (tokens[2].isEmpty()) {
                throw new IllegalArgumentException("The description of a task cannot be empty.");
            }
            String taskDescription = tokens[2].trim();

            switch (taskType) {
                case "T":
                    tasks.add(new Todo(taskDescription));
                    break;
                case "D":
                    String[] deadlineTokens = tokens[3].split(" ", 2);
                    if (deadlineTokens.length != 2) {
                        throw new IllegalArgumentException("Invalid deadline format in file.");
                    }
                    tasks.add(new Deadline(taskDescription, deadlineTokens[1]));
                    break;
                case "E":
                    String[] eventTokens = tokens[3].split(" ", 3);
                    if (eventTokens.length != 3) {
                        throw new IllegalArgumentException("Invalid event format in file.");
                    }
                    tasks.add(new Event(taskDescription, eventTokens[1], eventTokens[2]));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid task type in file!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    // Method to save tasks to a file
    public void save(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    // Method to load tasks from a file
    public List<Task> load() {
        List<Task> loadedTasks = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create parent directories if they don't exist
                file.createNewFile(); // Create the file if it doesn't exist

                System.out.println("No tasks file found. Created a new tasks file.");
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null) {
                    try {
                        addTaskFromFileString(line, loadedTasks);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error loading a task. Skipping invalid task line: " + line);
                    }
                }

                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());

            // Handle the situation where the file is corrupted
            System.out.println("Deleting the corrupted file and creating a new tasks file.");
            File corruptedFile = new File(FILE_PATH);
            corruptedFile.delete(); // Delete the corrupted file
            loadedTasks = load(); // Recursively call the method to create a new file
        }

        return loadedTasks;
    }
}
