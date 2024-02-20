package zack.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import zack.ZackException;
import zack.tasks.Deadline;
import zack.tasks.Event;
import zack.tasks.Task;
import zack.tasks.Todo;

/**
 * Handles saving and loading tasks from a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks will be saved and loaded.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to the specified file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If there is an error in writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Create directories if they don't exist
        try (FileWriter fw = new FileWriter(file, false)) {
            for (Task task : tasks) {
                fw.write(task.toFileString() + System.lineSeparator());
            }
        }
    }

    /**
     * Loads tasks from the specified file.
     *
     * @return An ArrayList of loaded tasks.
     * @throws ZackException If there is an error in reading or parsing the file.
     */
    public ArrayList<Task> load() throws ZackException {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = parseStringToTask(line);
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new ZackException("Hmm looks like you don't have a storage file yet. Don't worry, just"
                    + "start adding tasks to the task list and I'll create oen for you!");
        }
        return loadedTasks;
    }

    /**
     * Parses a line from the file into a Task object.
     *
     * @param line The line from the file to be parsed.
     * @return The parsed Task object.
     * @throws ZackException If there is an error in parsing the line.
     */
    public Task parseStringToTask(String line) throws ZackException {
        String[] parts = line.split(" \\| "); // split by " | "
        // handle exceptions for when the text file is edited.
        if (parts.length < 4) {
            throw new ZackException("Check your task formatting!");
        }
        String type = parts[0];
        boolean isDone = "1".equals(parts[1].trim());
        String description = parts[2];
        LocalDateTime addedTime;
        switch (type) {
        case "T":
            // ["T", "1", "homework", "2019-01-21T05:47:08.644"]
            addedTime = LocalDateTime.parse(parts[3]);
            return new Todo(description, isDone, addedTime);
        case "D":
            // ["D", "1", "assignment", "2022-08-01 2200", "2019-01-21T05:47:08.644"]
            if (parts.length < 5) {
                throw new ZackException("Check your deadline formatting!");
            }
            addedTime = LocalDateTime.parse(parts[4]);
            return new Deadline(description, parts[3], isDone, addedTime);
        case "E":
            if (parts.length < 5) {
                throw new ZackException("Check your event formatting!");
            }
            // ["E", "1", "project meeting", "2022-08-01 2200 to 2022-08-01 2300", "2019-01-21T05:47:08.644"]
            addedTime = LocalDateTime.parse(parts[4]);
            String[] fromTo = parts[3].split(" to ");
            String from = fromTo[0];
            String to = fromTo[1];
            return new Event(description, from, to, isDone, addedTime);
        default:
            throw new ZackException("There's an unknown task in your storage file! You might wana check it " +
                    "out and see what's going on...");
        }
    }
}
