package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The {@code Storage} class is responsible for loading and saving tasks to a file.
 */
public class Storage {
    private static String filePath;

    /**
     * Creates a new {@code Storage} object with the given file path.
     *
     * @param filePath The file path of the tasks file.
     */
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Loads the tasks from the tasks file.
     *
     * @return An {@code ArrayList} of {@code Task} objects representing the loaded tasks.
     * @throws DukeException If the tasks file cannot be found.
     */
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

    /**
     * Saves the given {@code TaskList} to the tasks file.
     *
     * @param tasks The {@code TaskList} to save.
     */
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

    /**
     * Creates a {@code Task} object from a line of text in the tasks file.
     *
     * @param line The line of text to parse.
     * @return A {@code Task} object, or {@code null} if the line is invalid.
     */
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
