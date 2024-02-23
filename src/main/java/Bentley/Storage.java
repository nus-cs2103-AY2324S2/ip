package Bentley;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    // Method to load tasks from file
    public void loadTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskDescription = scanner.nextLine();
                tasks.add(new Task(taskDescription));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    // Method to write tasks to file
    public void writeTasks(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
            System.out.println("Tasks written to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
