package secretaryw;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasksFromFile() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNext()) {
                    String taskData = fileScanner.nextLine();
                    Task task = createTaskFromData(taskData);
                    if (task != null) {
                        taskList.add(task);
                    }
                }
            }
        }
        return taskList;
    }

    public void saveTasksToFile(ArrayList<Task> taskList) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (Task task : taskList) {
                fileWriter.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private Task createTaskFromData(String taskData) {
        String[] parts = taskData.split("\\|");
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        if (type.equals("T")) {
            return new Task(TaskType.TODO, description, isDone);
        } else if (type.equals("D") || type.equals("E")) {
            if (parts.length < 4) {
                return null;
            }
            String by = parts[3].trim();
            if (type.equals("D")) {
                return new Task(TaskType.DEADLINE, description, by, isDone);
            } else {
                if (parts.length < 5) {
                    return null;
                }
                String to = parts[4].trim();
                return new Task(TaskType.EVENT, description, by, to, isDone);
            }
        }
        // Handle other types if needed
        return null;
    }
}
