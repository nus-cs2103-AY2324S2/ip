import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws ArtemisException {
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Task task = Task.fromFileString(line);
                    tasks.add(task);
                } catch (IllegalArgumentException e) {
                    // Log the error or handle it based on your application's needs
                    throw new ArtemisException("Error loading task from file: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            // Handle IO exception
            throw new ArtemisException("Error reading from file: " + e.getMessage());
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws ArtemisException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.println(task.toFileString());
            }
        } catch (IOException e) {
            // Handle IO exception
            throw new ArtemisException("Error writing to file: " + e.getMessage());
        }
    }
}
