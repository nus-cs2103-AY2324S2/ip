package linus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    // Referenced HusseinSafwan02's code and AI
    private static final String FILE_PATH = "./data/linus.txt";
    private static final String DIRECTORY_PATH = "./data";

    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);

            if (file.exists()) {
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = Parser.parseTask(line);

                    if (task != null) {
                        tasks.add(task);
                    }
                }

                scanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating a new file!");
        }

        return tasks;
    }

    public void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            File directory = new File(DIRECTORY_PATH);

            if (!directory.exists()) {
                directory.mkdirs(); // Create directory if it doesn't exist
            }

            FileWriter writer = new FileWriter(FILE_PATH);

            for (Task task : tasks) {
                writer.write(taskToFileString(task) + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to the file: " + e.getMessage());
        }
    }

    private String taskToFileString(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + event.getFrom() + " | " + event.getTo();
        } else {
            throw new IllegalArgumentException("Invalid task type!");
        }
    }
}
