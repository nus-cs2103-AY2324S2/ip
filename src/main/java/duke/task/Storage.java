/*
 * Storage.java
 * This class handles reading and writing tasks to a storage file in the Duke application.
 * It is responsible for loading tasks from a file and saving tasks to a file.
 */

package duke.task;

import duke.DukeException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Storage {
    private final String filePath;

    /**
     * Creates a new Storage instance with the specified file path.
     *
     * @param filePath The path to the storage file where tasks are stored.
     */
    public Storage(String filePath) {
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
        } catch (DukeException e) {
            throw new RuntimeException("Error parsing date-time: " + e.getMessage(), e);
        }
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
        return file;
    }

    /**
     * Saves the tasks from the TaskList to the storage file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws IOException If there is an issue writing to the file.
     */
    public void saveTasks(TaskList tasks) throws IOException {
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
        String type = task instanceof Todo ? "T" :
                    task instanceof Deadline ? "D" :
                    task instanceof Event ? "E" : "";
        String status = task.isDone() ? "1" : "0";
        String description = task.description;

        String details = type + " | " + status + " | " + description;
        if (task instanceof Deadline deadline) {
            details += " | " + deadline.by.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } else if (task instanceof Event) {
            details += " | " + ((Event) task).from + " | " + ((Event) task).to;
        }
        return details;
    }
}