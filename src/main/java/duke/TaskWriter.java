package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class TaskWriter {
    public static void saveTasksToFile(ArrayList<Task> tasks, String filePath) {
        try {
            createDirectory(filePath); // Create directory if it doesn't exist
            try (FileWriter writer = new FileWriter(filePath)) {
                for (Task task : tasks) {
                    writer.write(task.toFileString() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private static void createDirectory(String filePath) {
        File directory = new File(filePath).getParentFile();
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                System.out.println("Error creating directory: " + directory.getAbsolutePath());
            }
        }
    }
}