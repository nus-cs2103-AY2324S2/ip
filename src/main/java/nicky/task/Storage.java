package nicky.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import nicky.NickyException;

/**
 * Handles reading and writing tasks to a storage file in the Nicky application.
 * It is responsible for loading tasks from a file and saving tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a new Storage instance with the specified file path.
     *
     * @param filePath The path to the storage file where tasks are stored.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "FilePath cannot be null or empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file and returns them as a TaskList.
     *
     * @return The TaskList containing tasks loaded from the storage file.
     * @throws IOException If there is an issue reading the file.
     */
    public TaskList loadTasks() throws IOException {
        TaskList tasks = new TaskList();
        File file = getFile();
        assert file.exists() : "Storage file must exist after getFile() operation";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                Task task;
                switch (parts[0]) {
                case "T":
                    task = new Todo(parts[2]);
                    break;
                case "D":
                    LocalDateTime deadlineTime = DateTimeUtil.parseDateTime(parts[3]);
                    task = new Deadline(parts[2], deadlineTime);
                    break;
                case "E":
                    LocalDateTime startTime = DateTimeUtil.parseDateTime(parts[3]);
                    LocalDateTime endTime = DateTimeUtil.parseDateTime(parts[4]);
                    task = new Event(parts[2], startTime, endTime);
                    break;
                default:
                    continue;
                }
                if (parts[1].equals("1")) {
                    task.markAsDone();
                } else {
                    task.markAsNotDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new IOException("Error reading from file: " + filePath, e);
        } catch (NickyException e) {
            throw new RuntimeException("Error parsing date-time: " + e.getMessage(), e);
        }
        assert tasks != null : "TaskList should not be null after loading tasks";
        return tasks;
    }

    /**
     * Retrieves the File object for the storage file, creating directories and the file if necessary.
     *
     * @return The File object representing the storage file.
     * @throws IOException If there is an issue creating directories or the file.
     */
    private File getFile() throws IOException {
        File file = new File(filePath);

        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            boolean dirsCreated = parentDir.mkdirs();
            if (!dirsCreated) {
                throw new IOException("Unable to create directories: " + parentDir.getAbsolutePath());
            }
        }

        if (!file.exists()) {
            boolean fileCreated = file.createNewFile();
            if (!fileCreated) {
                throw new IOException("Unable to create new file: " + file.getAbsolutePath());
            }
        }
        assert file.exists() : "File must exist after creation attempt";
        return file;
    }

    /**
     * Saves the tasks from the TaskList to the storage file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws IOException If there is an issue writing to the file.
     */
    public void saveTasks(TaskList tasks) throws IOException {
        assert tasks != null : "TaskList to save cannot be null";
        File file = new File(filePath);
        assert file.exists() : "Storage file must exist before saving tasks";

        try (PrintWriter pw = new PrintWriter(filePath)) {
            for (Task task : tasks.getTasks()) {
                String line = taskToFileString(task);
                pw.println(line);
            }
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + filePath, e);
        }
    }

    /**
     * Converts a Task object to a string format suitable for saving in the storage file.
     *
     * @param task The Task object to be converted to a string.
     * @return The string representation of the Task for storage.
     */
    private String taskToFileString(Task task) {
        String type = "";
        if (task instanceof Todo) {
            type = "T";
        } else if (task instanceof Deadline) {
            type = "D";
        } else if (task instanceof Event) {
            type = "E";
        }

        String status = task.isDone() ? "1" : "0";
        String description = task.getDescription();

        String details = type + " | " + status + " | " + description;
        if (task instanceof Deadline) {
            details += " | " + ((Deadline) task).by.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } else if (task instanceof Event) {
            details += " | " + ((Event) task).from + " | " + ((Event) task).to;
        }
        return details;
    }
}
