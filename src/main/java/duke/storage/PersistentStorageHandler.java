package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import duke.commons.exceptions.DukeException;
import duke.commons.exceptions.TaskDataNotFoundException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Handles persistent storage operations for Duke application, including reading
 * from and writing tasks to disk.
 * Supports operations such as checking for the existence of the storage file,
 * creating it if necessary, reading tasks from it, and saving current tasks
 * back to it.
 */
public class PersistentStorageHandler {

    private static final String TASKLIST_PATH = "./tasklist.txt";

    /**
     * Ensures the existence of the task file and initializes it if not present.
     * 
     * @return True if the file exists or was successfully created, false if the
     *         file did not exist and was created.
     * @throws DukeException If creating the file fails.
     */
    public static boolean taskFileFound() throws DukeException {
        File file = new File(TASKLIST_PATH);

        if (!file.exists()) {
            try {
                file.createNewFile();
                return false;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw new DukeException("Failed to create persistent task file.");
            }
        }
        return true;
    }

    /**
     * Reads the task list from disk.
     * 
     * @return The task list loaded from the file.
     * @throws DukeException If the file does not exist or an error occurs during
     *                       reading.
     */
    public static TaskList readTaskFileFromDisc() throws DukeException {
        TaskList taskList = new TaskList();
        try (BufferedReader reader = new BufferedReader(new FileReader(TASKLIST_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskFromString(line);
                if (task != null) {
                    taskList.addTask(task);
                }
            }
        } catch (Exception e) {
            throw new TaskDataNotFoundException(
                    "File: " + TASKLIST_PATH + " not found.\nWelcome to your new productivity journey.");
        }
        return taskList;
    }

    private static Task parseTaskFromString(String line) throws DukeException {
        try {
            String[] parts = line.split("🦫");
            boolean isDone;
            if (parts.length == 2) {
                String description = parts[0];
                isDone = Boolean.valueOf(parts[1]);
                Task task = new ToDo(description);
                if (isDone) {
                    task.markDone();
                }
                return task;
            } else if (parts.length == 3) {
                String description = parts[0];
                isDone = Boolean.valueOf(parts[1]);
                String due = parts[2];
                Task task = new Deadline(description, due);
                if (isDone) {
                    task.markDone();
                }
                return task;
            } else if (parts.length == 4) {
                String description = parts[0];
                isDone = Boolean.valueOf(parts[1]);
                String start = parts[2];
                String end = parts[3];
                Task task = new Event(description, start, end);
                if (isDone) {
                    task.markDone();
                }
            } else {
                throw new DukeException("Unabe to parse Task from String: " + line);
            }
        } catch (Exception e) {
            throw new DukeException("Unabe to parse Task from String: " + line);
        }
        return null;
    }

    /**
     * Writes the current task list to disk.
     * 
     * @param taskList The task list to be written to disk.
     * @throws DukeException If an error occurs during writing.
     */
    public static void writeTaskFileToDisc(TaskList taskList) throws DukeException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TASKLIST_PATH))) {
            for (Task task : taskList.getTasks()) {
                writer.write(task.serialize());
                writer.newLine();
            }
        } catch (Exception e) {
            throw new DukeException("Failed to write tasks to file: " + TASKLIST_PATH);
        }
    }
}
