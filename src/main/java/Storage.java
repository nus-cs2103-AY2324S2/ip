import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(List<Task> tasks) {
        try {

            File directory = new File("." + File.separator + "data");
            if (!directory.exists()) {
                directory.mkdirs();  // Create the directory if it doesn't exist
            }

            File file = new File(directory, "ken.txt");
            if (!file.exists()) {
                file.createNewFile();  // Create the file if it doesn't exist
            }

            try (FileWriter writer = new FileWriter(file)) {
                for (Task task : tasks) {
                    writer.write(task.toFileString() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public List<Task> loadTask() throws KenException {
        try {
            File directory = new File("." + File.separator + "data");
            if (!directory.exists()) {
                directory.mkdirs();  // Create the directory if it doesn't exist
                return new ArrayList<>();  // Return early, no need to load tasks from a non-existent file
            }

            File file = new File(directory, "ken.txt");
            if (!file.exists()) {
                return new ArrayList<>();  // Return early, no need to load tasks from a non-existent file
            }

            List<Task> tasks = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = Task.parseFromFileString(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new KenException("Error loading tasks from file: " + e.getMessage());
        }
    }
}
