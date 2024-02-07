package duke;

import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Storage {
    private static String filePath;

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = createTaskFromLine(line);
                if (task != null) {
                    list.add(task);
                }
            }
        } catch (FileNotFoundException | NoSuchElementException e) {
            throw new DukeException("File not found: " + e.getMessage());
        }
        return list;
    }

    public void save(TaskList tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            int size = tasks.size();
            for (int i = 0; i < size; i++) {
                Task t = tasks.get(i);
                writer.write(t.toStringForFile() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
    private static Task createTaskFromLine(String line) {
        Task t = null;
        String[] parts = line.split("\\|");
        String taskType = parts[0].trim();
        String taskStatus = parts[1].trim();
        String taskDescription = parts[2].trim();
        switch (taskType) {
            case "T":
                t = new Todo(taskDescription);
                break;
            case "D":
                String taskBy = parts[3].trim();
                LocalDateTime taskDeadline = LocalDateTime.parse((taskBy));
                t = new Deadline(taskDescription, taskDeadline);
                break;
            case "E":
                LocalDateTime taskFrom = LocalDateTime.parse(parts[3].trim());
                LocalDateTime taskTo = LocalDateTime.parse(parts[4].trim());
                t = new Event(taskDescription, taskTo,  taskFrom);
                break;
            default:
                System.out.println("Invalid task type: " + taskType);
        }
        if (taskStatus.equals("1")) {
            t.markDone();
        }
        return t;
    }
}
