package talktomeorilldie;

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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the storage class of the TalkToMeOrIllDie program.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage.
     * @param filePath The file path to store the tasks.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path cannot be null";
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
        File file = new File(filePath);
        if (file.getParentFile().mkdirs()) {
            System.out.println("Directories created successfully.");
        } else {
            System.out.println("Saving it to your already created directories");
        }

        // Initialize writer outside try block
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < taskNum; i++) {
                writer.write(tasks[i].toSaveString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads the tasks from the file.
     * @return The tasks loaded from the file.
     * @throws IOException If there is an error loading the tasks.
     */
    public Task[] loadTasks() throws IOException {
        Path file = Paths.get(this.filePath);
        if (!Files.exists(file)) {
            return new Task[100];
        }

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
    }

    private Task parseTaskFromLine(String line) {
        char taskType = line.charAt(0);
        boolean isDone = line.charAt(4) == '1';

        int separation1 = line.indexOf("|", 4);

        Task task;
        switch (taskType) {
        case 'T':
            String descriptionTodo = line.substring(separation1 + 2);
            task = new Todo(descriptionTodo);
            break;
        case 'D':
            int separationDeadline = line.indexOf(" | ", separation1 + 3);

            String descriptionDeadline = (separationDeadline != -1) ? line.substring(separation1 + 2,
                    separationDeadline) : line.substring(separationDeadline + 3);
            task = parseDeadlineFromLine(line, descriptionDeadline);
            break;
        case 'E':
            int separationEvent = line.indexOf(" | ", separation1 + 3);
            String descriptionEvent = (separationEvent != -1) ? line.substring(separation1 + 2, separationEvent)
                    : line.substring(separationEvent + 3);
            task = parseEventFromLine(line, descriptionEvent, separationEvent);
            break;
        default:
            throw new IllegalArgumentException("Invalid task type");
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    private Task parseDeadlineFromLine(String line, String description) {
        String dateString = line.substring(line.lastIndexOf("|") + 1).trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime deadlineDateTime = LocalDateTime.parse(dateString, formatter);

        return new Deadline(description, deadlineDateTime);
    }

    private Task parseEventFromLine(String line, String description, int separationEvent) {
        int to = line.indexOf(" - ", separationEvent + 3);
        int spacing = line.indexOf(" ", separationEvent + 3);
        String fromDate = line.substring(separationEvent + 3, spacing);
        String fromTime = line.substring(spacing + 1, to);
        String to2 = line.substring(to + 3);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime deadlineFrom = LocalTime.parse(fromTime, formatter);
        LocalTime deadlineTo = LocalTime.parse(to2, formatter);

        return new Event(description, fromDate, deadlineFrom, deadlineTo);
    }
}
