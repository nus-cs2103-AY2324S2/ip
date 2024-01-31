package storage;
import java.io.*;

import tasks.Task;
import tasks.TaskList;

public class Storage {
    private final String filePath;
    private final TaskList taskList;

    public Storage(String filePath, TaskList taskList) {
        this.filePath = filePath;
        this.taskList = taskList;
    }

    public void writeToFile() throws IOException {
        try {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Task task : taskList.getTaskList()) {
            writer.write(task.toString());
            writer.newLine();
        }
        writer.close();
    } catch (IOException e) {
        System.out.println("Error writing to file.");
    }
    }

    public String generateTasks() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        StringBuilder tasks = new StringBuilder();
        int i = 1;
        while ((line = reader.readLine()) != null) {
            tasks.append(i++ + ". ").append(line).append("\n");
        }
        reader.close();
        return tasks.toString();
    }
}