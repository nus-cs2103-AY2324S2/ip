import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static final String FILE_PATH = "data/duke.txt";
    private List<Task> taskLog;

    public TaskManager() {
        this.taskLog = new ArrayList<>();
        loadTasks();
    }

    private void loadTasks() {
        try {
            Path filePath = Paths.get(FILE_PATH);

            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
                System.out.println("Info: Data file not found. Created a new file.");
            }

            String content = Files.readString(filePath);

            if (isValidContentFormat(content)) {
                // Parse and process the content
                System.out.println("Loading tasks...");
                // Example: parseContent(content);
                System.out.println("Tasks loaded successfully.");
            } else {
                System.out.println("Error: Data file content is corrupted.");
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to load data. " + e.getMessage());
        }
    }

    private boolean isValidContentFormat(String content) {
        // Implement your logic to check whether the content format is valid
        // For simplicity, this example assumes a string representation of tasks.
        return true;
    }

    private void saveTasks() {
        try {
            StringBuilder tasksContent = new StringBuilder();
            for (Task task : taskLog) {
                tasksContent.append(task.toString()).append("\n");
            }

            Files.write(Paths.get(FILE_PATH), tasksContent.toString().getBytes());
            System.out.println("Tasks saved successfully.");
        } catch (IOException e) {
            System.out.println("Error: Unable to save data. " + e.getMessage());
        }
    }

    public void addTask(Task task) {
        taskLog.add(task);
        saveTasks();
    }

    public void removeTask(int index) {
        if (index >= 0 && index < taskLog.size()) {
            taskLog.remove(index);
            saveTasks();
        } else {
            System.out.println("Error: Invalid task index.");
        }
    }

    public List<Task> getTaskLog() {
        return taskLog;
    }

    // Other methods for task management...

    // Example method for parsing content (customize based on your actual content format)
    private void parseContent(String content) {
        String[] lines = content.split("\n");
        for (String line : lines) {
            // Example: create Task object based on line and add it to taskLog
            // For simplicity, this example assumes a string representation of tasks.
        }
    }
}
