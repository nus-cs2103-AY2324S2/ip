package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Handles loading and saving of tasks to a file.
 */
public class Storage {
    private static final String FILE_PATH = "./duke.txt";
    private String filePath;

    /**
     * Constructs a Storage object with a given file path.
     */
    public Storage() {
        assert filePath != null && !filePath.trim().isEmpty() : "File path cannot be null or empty";
        this.filePath = FILE_PATH;
    }


    /**
     * Loads tasks from the file.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws DukeException If there is an error loading the file.
     */
    public ArrayList<Task> load() throws DukeException {
        ensureDirectoryExists();
        ArrayList<Task> tasks = new ArrayList<>();
        File storageFile = new File(this.filePath);

        // Create the file if it does not exist
        if (!storageFile.exists()) {
            try {
                storageFile.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Error Creating File: " + e.getMessage());
            }
        }

        // Load tasks from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(storageFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseLineToTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new DukeException("Error Loading Tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks The ArrayList of tasks to be saved.
     * @throws DukeException If there is an error saving the file.
     */
    public void save(ArrayList<Task> tasks) throws DukeException {
        ensureDirectoryExists();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath))) {
            for (Task task : tasks) {
                writer.write(task.saveData());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new DukeException("Error Storing Tasks: " + e.getMessage());
        }
    }

    /**
     * Ensures that the directory for the file exists.
     *
     * @throws DukeException If there is an error creating the directory.
     */
    private void ensureDirectoryExists() throws DukeException {
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                Files.createDirectories(Paths.get(this.filePath).getParent());
            }
        } catch (IOException e) {
            throw new DukeException("Error Creating Directory: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the file to a task.
     *
     * @param line The line to be parsed.
     * @return The task parsed from the line.
     * @throws DukeException If the line is not in a valid format.
     */
    private static Task parseLineToTask(String line) throws DukeException {
        // Assuming the format is "T | 1 | read book"
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new DukeException("Invalid task format in file");
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            Todo todo = new Todo(description);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;
        case "D":
            if (parts.length < 4) {
                throw new DukeException("Invalid deadline format in file");
            }
            LocalDate byDate = LocalDate.parse(parts[3], DateTimeFormatter.ISO_LOCAL_DATE);
            Deadline deadline = new Deadline(description, byDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            if (isDone) {
                deadline.markAsDone();
            }
            return deadline;
        case "E":
            if (parts.length < 5) {
                throw new DukeException("Invalid event format in file");
            }
            LocalDate fromDate = LocalDate.parse(parts[3], DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate toDate = LocalDate.parse(parts[4], DateTimeFormatter.ISO_LOCAL_DATE);
            Event event = new Event(description, fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            if (isDone) {
                event.markAsDone();
            }
            return event;
        default:
            throw new DukeException("Unknown task type: " + type);
        }
    }
}
