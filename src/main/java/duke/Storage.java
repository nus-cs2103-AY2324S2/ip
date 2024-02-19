package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the storage class of the Duke program.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage.
     * @param filePath The file path to store the tasks.
     */
    public Storage(String filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException("File path cannot be null");
        }
        this.filePath = filePath;
    }

    /**
     * Saves the tasks to the file.
     * @param tasks The tasks to be saved.
     * @param taskNum The number of tasks to be saved.
     */
    public void saveTasksToFile(Task[] tasks, int taskNum) {
        assert tasks != null : "Tasks array cannot be null";
        assert taskNum >= 0 : "Invalid task number";
        try {
            File fileReader = new File(filePath);
            if (fileReader.getParentFile().mkdirs()) {
                System.out.println("Directories created successfully.");
            } else {
                System.out.println("Saving it to your already created directories");
            }

            // Initialize writer outside try block
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileReader))) {

                for (int i = 0; i < taskNum; i++) {
                    writer.write(tasks[i].toSaveString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("can't save :((");
        }
    }

    /**
     * Loads the tasks from the file.
     * @return The tasks loaded from the file.
     * @throws IOException If there is an error loading the tasks.
     */
    public Task[] loadTasks() throws IOException {
        try {
            Path file = Paths.get(filePath);
            assert Files.exists(file) : "File does not exist";

            Task[] loadedTasks = new Task[100];
            int loadedTaskNum = 0;

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    loadedTasks[loadedTaskNum] = parseTaskFromLine(line);
                    loadedTaskNum++;
                }
            }
            return loadedTasks;
        } catch (IOException e) {
            throw new IOException("Error loading tasks", e);
        }
    }

    /**
     * Parses the task from the line.
     * @param line The line to parse the task from.
     * @return The task parsed from the line.
     */
    private Task parseTaskFromLine(String line) {
        char taskType = line.charAt(0);
        boolean isDone = line.charAt(4) == '1';

        int sep1 = line.indexOf("|", 4);
        String description = line.substring(sep1 + 2);

        Task task;
        switch (taskType) {
        case 'T':
            task = new Todo(description);
            break;
        case 'D':
            task = parseDeadlineFromLine(line, description);
            break;
        case 'E':
            task = parseEventFromLine(line, description, sep1);
            break;
        default:
            throw new IllegalArgumentException("Invalid task type");
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Parses the deadline from the line.
     * @param line The line to parse the deadline from.
     * @param description The description of the deadline.
     * @return The deadline parsed from the line.
     */
    private Task parseDeadlineFromLine(String line, String description) {
        String dateString = line.substring(line.lastIndexOf("|") + 1).trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime deadlineDateTime = LocalDateTime.parse(dateString, formatter);

        return new Deadline(description, deadlineDateTime);
    }

    /**
     * Parses the event from the line.
     * @param line The line to parse the event from.
     * @param description The description of the event.
     * @param sep1 The index of the first separator.
     * @return The event parsed from the line.
     */
    private Task parseEventFromLine(String line, String description, int sep1) {
        int sep2 = line.indexOf(" | ", sep1 + 3);
        int to = line.indexOf(" - ", sep2 + 3);
        String from = line.substring(sep2 + 3, to);
        String too = line.substring(to + 3);
        return new Event(description, from, too);
    }
}
