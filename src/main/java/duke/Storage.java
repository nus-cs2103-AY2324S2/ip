package duke;

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    Task task = fileStringToTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
                scanner.close();
            }
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    private static Task fileStringToTask(String line) throws DukeException {
        // Convert a string from a file back to a task
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null; // Not enough parts to construct a task
        }
        String type = parts[0];
        boolean isDone = "1".equals(parts[1]);
        String description = parts[2];

        Task task = null;
        switch (type) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                task = new Deadline(description, parts.length > 3 ? parts[3] : "");
                break;
            case "E":
                task = new Event(description, parts.length > 3 ? parts[3] : "", parts.length > 4 ? parts[4] : "");
                break;
        }
        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }

    public void save(TaskList tasks) throws DukeException {
        try {
            PrintWriter writer = new PrintWriter(filePath);

            for (int i = 0; i < tasks.size(); i++) {
                writer.println(taskToFileString(tasks.getTask(i)));
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String taskToFileString(Task task) {
        String type = task instanceof ToDo ? "T" :
                task instanceof Deadline ? "D" :
                        task instanceof Event ? "E" : "";
        String status = task.isDone ? "1" : "0";
        String details = type + " | " + status + " | " + task.description;

        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            details += " | " + deadline.getByForFile();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            details += " | " + event.getStartForFile() + " | " + event.getEndForFile();
        }

        return details;
    }
}
