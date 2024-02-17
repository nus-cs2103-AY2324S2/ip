import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Storage {
    private final String filePath;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file, false);
            for (Task task : tasks) {
                writer.write(taskToFileFormat(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An unexpected error occurred while saving tasks!");
        }
    }

    private String taskToFileFormat(Task task) {
        String type = task instanceof Todo ? "T" : task instanceof Deadline ? "D" : "E";
        String status = task.isDone() ? "1" : "0";
        String details = task.getDescription();
        String time = "";
        if (task instanceof Deadline) {
            LocalDateTime by = ((Deadline) task).getBy();
            time = by.format(formatter);
        } else if (task instanceof Event) {
            time = ((Event) task).getTime();
        }
        return type + " | " + status + " | " + details + (time.isEmpty() ? "" : " | " + time);
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                Task task = parseTask(data);
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No existing task file found. Starting a new task list.");
        }
        return loadedTasks;
    }

    private Task parseTask(String data) {
        String[] parts = data.split(" \\| ");
        if (parts.length < 3) return null;

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String name = parts[2];
        Task task = null;

        switch (type) {
            case "T":
                task = new Todo(name, isDone);
                break;
            case "D":
                if (parts.length >= 4) {
                    String byString = parts[3];
                    LocalDateTime by = LocalDateTime.parse(byString, formatter);
                    task = new Deadline(name, isDone, by);
                }
                break;
            case "E":
                if (parts.length >= 4) {
                    String time = parts[3];
                    task = new Event(name, isDone, time);
                }
                break;
        }
        return task;
    }
}
