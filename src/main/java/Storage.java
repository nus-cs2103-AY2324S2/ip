import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //Load data from file --> Change to just return task list itself...
    public ArrayList<Task> loadData() throws DuchessException {
        TaskList taskList = new TaskList();
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                file.createNewFile(); // Create file if it doesn't exist
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Task task = parseTaskFromFileString(line); // Parse task from file line
                if (task != null) {
                    taskList.getTasks().add(task); // Add task to the list
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return taskList.getTasks();
    }

    //Saves the data to the file after every change, rewriting each task in the list
    public void saveData(TaskList taskList) {
        try (FileWriter writer = new FileWriter(this.filePath)) {
            for (Task task : taskList.getTasks()) {
                writer.write(task.toFileString() + "\n"); // Write each task to file
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }


    // Method to parse task from a line read from file
    private Task parseTaskFromFileString(String line) throws DuchessException {
        Task task = null;
        // Parse the line and create task objects accordingly
        // Example line format: "T | 1 | read book"
        String[] parts = line.split("\\|");
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        switch (type) {
            case "T":
                task = new ToDo(description, isDone);
                break;
            case "D":
                String by = parts[3].trim();
                task = new Deadline(description, isDone, by);
                break;
            case "E":
                String from = parts[3].trim();
                String to = parts[4].trim();
                task = new Event(description, isDone, from, to);
                break;
            default:
                System.out.println("Unknown task type: " + type);
        }
        return task;
    }


}
