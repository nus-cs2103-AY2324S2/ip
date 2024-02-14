package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskStatus;
import duke.task.TaskType;
import duke.task.ToDo;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * The Storage class handles the saving and loading of tasks to and from a data file.
 * It uses a specific file format to store task details in a text file.
 */
public class Storage {
    /** The file path for saving and loading tasks. */
    static final String FILE_PATH = "./data/duke.txt";
    private static File file = new File(FILE_PATH);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /** The TaskList used to store tasks during the program's execution. */
    private Ui ui = new Ui();
    private TaskList taskList = new TaskList(new ArrayList <Task>(), this.ui);

    /**
     * Saves the tasks from the given TaskList to the data file specified by FILE_PATH.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveTasks(TaskList tasks) throws IOException {
        String text;
        File parentFolder = file.getParentFile();
        if (!parentFolder.exists()) {
            if (!parentFolder.mkdirs()) {
                throw new IOException("Failed to create the data folder.");
            }
        }
        try (FileWriter fileWriter = new FileWriter(file, false)) {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.getTask(i);
                text = storeTasks(task);
                fileWriter.write(text);
                fileWriter.append("\n");
            }
        }
    }

    /**
     * Generates a formatted string representation of a task for saving to the data file.
     *
     * @param task The task to be converted to a string for storage.
     * @return A formatted string representing the task.
     * @throws IOException If an I/O error occurs while storing the task.
     */
    public String storeTasks(Task task) throws IOException {
        String text;
        if (task instanceof Deadline) {
            text = storeDeadline(task);
        } else if (task instanceof Event) {
            text = storeEvent(task);
        } else {
            text = storeToDo(task);
        }
        return text;
    }

    /**
     * Generates a formatted string representation of a ToDo task for storage.
     *
     * @param task The ToDo task to be converted to a string for storage.
     * @return A formatted string representing the ToDo task.
     */
    public String storeToDo(Task task) {
        return task.getType() + " | " + (task.getStatusIcon().equals("X") ? TaskStatus.DONE : TaskStatus.NOT_DONE)
                + " | " + task.getDescription();
    }
    /**
     * Generates a formatted string representation of a Deadline task for storage.
     *
     * @param task The Deadline task to be converted to a string for storage.
     * @return A formatted string representing the Deadline task.
     */
    public String storeDeadline(Task task) {
        Deadline deadlineTask = (Deadline) task;
        String text = task.getType() + " | " + (task.getStatusIcon().equals("X") ? TaskStatus.DONE
                : TaskStatus.NOT_DONE) + " | " + task.getDescription();
        if (deadlineTask.getBy() != null) {
            LocalDateTime byTime = deadlineTask.getByTime();
            String formattedDateTime = (byTime != null)
                    ? byTime.format(formatter) : deadlineTask.getByString();

            text += " | " + formattedDateTime;
        }
        return text;
    }

    /**
     * Generates a formatted string representation of an Event task for storage.
     *
     * @param task The Event task to be converted to a string for storage.
     * @return A formatted string representing the Event task.
     */
    public String storeEvent(Task task) {
        Event event = (Event) task;
        String text = task.getType() + " | " + (task.getStatusIcon().equals("X")
                ? TaskStatus.DONE : TaskStatus.NOT_DONE) + " | " + task.getDescription();
        String fromString = (event.getFromTime() != null)
                ? event.getFromTime().format(formatter) : (event.getFromString() != null ? event.getFromString() : "");

        String toString = (event.getToTime() != null)
                ? event.getToTime().format(formatter) : (event.getToString() != null ? event.getToString() : "");

        return text += " | " + fromString + " - " + toString;
    }


    /**
     * Loads tasks from the data file specified by FILE_PATH and returns them as an ArrayList.
     *
     * @return An ArrayList containing tasks loaded from the data file.
     * @throws DukeException If there is an issue loading tasks from the file.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            System.out.println("\nData file does not currently exist. However, as you add a task, it will save it to"
                    + "\nthe " + "path specified. You can view your task list after exiting the chatbot.");
            return loadedTasks;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                loadedTasks.add(parseTask(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return loadedTasks;
    }

    /**
     * Parses a line from the data file and converts it into a Task object.
     *
     * @param line The line from the data file representing a task.
     * @return A Task object parsed from the line.
     * @throws DukeException If there is an issue parsing the line into a Task object.
     */
    private Task parseTask(String line) throws DukeException {
        String[] parts = line.split("\\|");
        TaskType taskType = TaskType.valueOf(parts[0].trim());
        String description = parts[2].trim();
        String additionalInfo = (parts.length > 3) ? parts[3].trim() : null;

        switch (taskType) {
        case T:
            return new ToDo(description, ui);
        case D:
            return new Deadline(description, additionalInfo, ui);
        case E:
            String[] p = additionalInfo.split("-");
            return new Event(description, p[0].trim(), p[1].trim(), ui);
        default:
            return new Task(null, null);
        }
    }

}
