package storage;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the storage of task data in a file.
 * This class is responsible for loading and saving tasks to and from a file.
 * It supports tasks of types Todo, Deadline, and Event.
 * The file format used is text-based with specific delimiters for task attributes.
 *
 * @author Muhammad Rizki Bayuaji
 */
public class Storage {
    private static final String FILE_PATH = "./src/main/java/Storage/data.txt";

    /**
     * Loads tasks from the file.
     * Parses the text file content and creates corresponding Task objects.
     *
     * @return A list of tasks loaded from the file.
     *
     * @author Muhammad Rizki Bayuaji
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            assert file.exists() : "Missing file was not made";
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskComponents = line.split(" \\| ");
                switch (taskComponents[0]) {
                    case "T":
                        Todo todo = new Todo(taskComponents[2]);
                        if (taskComponents[1].equals("1")) todo.setMark();
                        tasks.add(todo);
                        break;
                    case "D":
                        try {
                            Deadline deadline = new Deadline(taskComponents[2], taskComponents[3]);
                            if (taskComponents[1].equals("1")) deadline.setMark();
                            tasks.add(deadline);
                        } catch (DateTimeParseException e) {
                            System.out.println("Error parsing date for task '" + taskComponents[2] + "': " + e.getMessage());
                        }
                        break;
                    case "E":
                        Event event = new Event(taskComponents[2], taskComponents[3], taskComponents[4]);
                        if (taskComponents[1].equals("1")) event.setMark();
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

    /**
     * Saves tasks to the file.
     * Converts tasks to a string format and writes them to the file.
     *
     * @param tasks The list of tasks to be saved.
     *
     * @author Muhammad Rizki Bayuaji
     */

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
    /**
     * Converts a Task object into a string format suitable for file storage.
     * The format depends on the type of the task (Todo, Deadline, Event).
     *
     * @param task The task to be converted into a string format.
     * @return The formatted string representing the task.
     *
     * @author Muhammad Rizki Bayuaji
     */
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
