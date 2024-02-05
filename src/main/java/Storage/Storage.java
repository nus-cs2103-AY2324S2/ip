package Storage;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String FILE_PATH = "./src/main/java/Storage/data.txt";
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        Todo todo = new Todo(parts[2]);
                        if (parts[1].equals("1")) todo.mark();
                        tasks.add(todo);
                        break;
                    case "D":
                        try {
                            Deadline deadline = new Deadline(parts[2], parts[3]);
                            if (parts[1].equals("1")) deadline.mark();
                            tasks.add(deadline);
                        } catch (DateTimeParseException e) {
                            System.out.println("Error parsing date for task '" + parts[2] + "': " + e.getMessage());
                        }
                        break;
                    case "E":
                        Event event = new Event(parts[2], parts[3], parts[4]);
                        if (parts[1].equals("1")) event.mark();
                        tasks.add(event);
                        break;
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    public void saveTasks(List<Task> tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Task task : tasks) {
                writer.write(taskToFileString(task));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private String taskToFileString(Task task) {
        String type = task instanceof Todo ? "T" : task instanceof Deadline ? "D" : "E";
        String status = task.checkDone() ? "1" : "0";
        String description = task.getDescription();
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            String formattedBy = deadline.getBy().format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return type + " | " + status + " | " + description + " | " + formattedBy;
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return type + " | " + status + " | " + description + " | " + event.getStart() + " | " + event.getEnd();
        } else {
            return type + " | " + status + " | " + description;
        }
    }
}
