import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {

    public Storage() {
        try {
            String projectPath = System.getProperty("user.dir");
            java.nio.file.Path filePath = java.nio.file.Paths.get(projectPath, "src",
                    "main", "resources", "data", "bmo_data.txt");
            boolean isFileExists = java.nio.file.Files.exists(filePath);
            if (!isFileExists) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
                System.out.println("Info: Data file not found. Created a new file.");
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to load data. " + e.getMessage());
        }
    }

    public String loadData() throws IOException{
        String projectPath = System.getProperty("user.dir");
        java.nio.file.Path filePath = java.nio.file.Paths.get(projectPath, "src",
                "main", "resources", "data", "bmo_data.txt");
        return Files.readString(filePath);
    }

    public void saveData(TaskList tasks) {
        try {
            StringBuilder tasksContent = new StringBuilder();
            for (Task task : tasks) {
                tasksContent.append(task.toSaveData());
            }

            String projectPath = System.getProperty("user.dir");
            java.nio.file.Path filePath = java.nio.file.Paths.get(projectPath, "src",
                    "main", "resources", "data", "bmo_data.txt");

            Files.createDirectories(filePath.getParent());
            Files.write(filePath, tasksContent.toString().getBytes());
            System.out.println("Tasks saved successfully.");
        } catch (IOException e) {
            System.out.println("Error: Unable to save data. " + e.getMessage());
        }
    }
}
