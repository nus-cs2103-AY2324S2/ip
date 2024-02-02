import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Create directories if they don't exist
        try (FileWriter fw = new FileWriter(file, false)) {
            for (Task task : tasks) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
        }
    }

    public ArrayList<Task> load() throws ZackException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new ZackException("Task file not found. A new file will be created upon adding to task list.");
        }
        return loadedTasks;
    }

    public Task parseTask(String line) throws ZackException {
        String[] parts = line.split(" \\| ");
        // handle exceptions for when the text file is edited.
        if (parts.length < 3) {
            throw new ZackException("Invalid task format");
        }
        String type = parts[0];
        boolean isDone = "1".equals(parts[1].trim());
        String description = parts[2];
        switch (type) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            if (parts.length < 4) throw new ZackException("Invalid deadline format");
            return new Deadline(description, parts[3], isDone);
        case "E":
            if (parts.length < 4) throw new ZackException("Invalid event format");
            // ["E", "1", "project meeting", "Aug 5th to Aug 6th"]
            String[] from_to = parts[3].split(" to ");
            String from = from_to[0];
            String to = from_to[1];
            return new Event(description, from, to, isDone);
        default:
            throw new ZackException("Unknown task type");
        }
    }
}
