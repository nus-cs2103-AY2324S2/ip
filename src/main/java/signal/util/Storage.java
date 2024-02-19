package signal.util;

import signal.task.*;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    private static String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() {
        TaskList taskList = new TaskList();

        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                // Create the file and necessary directory structure if it doesn't exist
                file.getParentFile().mkdirs();
                file.createNewFile();
                return taskList.giveList(); // Return an empty list since there are no tasks yet
            }

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                // Parse each line and create Task objects
                Task task = parseTask(line);
                if (task != null) {
                    taskList.addTask(task);
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }

        return taskList.giveList();
    }

    public static Task parseTask(String line) {
        // Parse each line to create Task objects
        // Example line format: "T | 1 | read book"
        String[] parts = line.split(" \\| ");
        if (parts.length >= 3) {
            boolean isDone = parts[1].equals("X");
            String description = parts[2];
            if (parts[0] == "T") { // todo
                return new ToDo(description);
            } else if (parts[0] == "D") { // deadline
                String deadline = parts[3];
                return new Deadline(description, deadline);
            } else if (parts[0] == "E") { // event
                String from = parts[3];
                String to = parts[4];
                return new Event(description, from, to);
            }

        } else {
            System.out.println("Invalid line format: " + line);
            return null;
        }
        return null;
    }

    public void writeTasks(ArrayList<Task> taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));

            for (Task task : taskList) {
                // Write each task to a line in the file
                writer.write(task.toString());
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
