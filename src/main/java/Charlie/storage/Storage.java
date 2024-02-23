package Charlie.storage;

import Charlie.exceptions.CharlieException;
import Charlie.models.Deadline;
import Charlie.models.Event;
import Charlie.models.Task;
import Charlie.models.Todo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    private String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws CharlieException{
        ArrayList<Task> loadedTasks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    loadedTasks.add(parseTaskFromFile(line));
                } catch (IllegalArgumentException e) {
                    throw new CharlieException("Found a corrupted task in save file, skipping: " + line);
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new CharlieException("No saved tasks found, starting with an empty list.");
        }
        return loadedTasks;
    }

    private Task parseTaskFromFile(String line) throws IllegalArgumentException {
        String[] parts = line.split(" \\| ");
        Task task = null;

        switch (parts[0]) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            default:
                throw new IllegalArgumentException("Invalid task type in file.");
        }

        if (parts[1].equals("1")) task.markAsDone();
        return task;
    }

    public void saveTasks(ArrayList<Task> taskList) throws CharlieException {
        try {
            Files.createDirectories(Paths.get("./data"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            for (Task task : taskList) {
                writer.write(taskToFileFormat(task));
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            throw new CharlieException("An error occurred while saving tasks to disk.");
        }
    }

    private String taskToFileFormat(Task task) {
        String type = task instanceof Todo ? "T" :
                task instanceof Deadline ? "D" :
                        task instanceof Event ? "E" : "";
        int done = task.isDone() ? 1 : 0;
        String details = task.getDescription();

        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            details += " | " + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            details += " | " + event.getStartsAt() + " | " + event.getEndsAt();
        }

        return String.join(" | ", type, String.valueOf(done), details);
    }
}
