package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.stream.IntStream;

import org.json.JSONArray;

import duke.Duke;
import duke.exceptions.InvalidArgumentException;
import duke.exceptions.MissingTaskException;
import duke.exceptions.StorageException;
import duke.exceptions.TaskCorruptedException;
import duke.exceptions.TaskNotSupportedException;
import duke.ui.Cli;

/**
 * The TaskList class handles storing of tasks required for
 * the application
 *
 * @author Ryan NgWH
 */
public class TaskList {
    /**
     * Array used to store tasks for the application
     */
    private ArrayList<Task> taskArray = new ArrayList<>();

    /**
     * Create a new task list with values loaded from a file
     *
     * @param file File to load tasks from
     */
    public TaskList(File file) {
        try {
            Storage.loadFromFile(this, file);
        } catch (TaskNotSupportedException | TaskCorruptedException | FileNotFoundException e) {
            Cli.printLoadFromFileWarning();
        }
    }

    /**
     * Get the number of stored tasks
     *
     * @return Number of stored tasks
     */
    public int size() {
        return taskArray.size();
    }

    /**
     * Adds a task
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        taskArray.add(task);
    }

    /**
     * Remove an item from task list
     *
     * @param deleteIndex Index of the item to delete
     *
     * @return Deleted task
     */
    public Task deleteTask(int deleteIndex) throws StorageException, MissingTaskException {
        try {
            // Delete item
            Task deletedTask = taskArray.remove(deleteIndex);

            // Save to file
            saveTasks();

            return deletedTask;
        } catch (IndexOutOfBoundsException e) {
            throw new MissingTaskException("Task does not exist");
        }
    }

    /**
     * Mark a task as completed/uncompleted
     *
     * @param markIndex   Index of the item to mark
     * @param isCompleted Status of the task
     *
     * @return Task succesfully marked as completed
     */
    public Task markTask(int markIndex, boolean isCompleted) throws StorageException, MissingTaskException {
        try {
            if (isCompleted) { // Mark task
                taskArray.get(markIndex).mark();
            } else { // Unmark task
                taskArray.get(markIndex).unmark();
            }

            // Save to file
            saveTasks();

            return taskArray.get(markIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingTaskException("Task does not exist");
        }
    }

    /**
     * Get all items in storage
     *
     * @return String containing all items in storage
     */
    public String getTasks() {
        StringBuilder tasks = new StringBuilder();

        IntStream
                .range(0, taskArray.size())
                .mapToObj(index -> String.format("%d.%s\n", index + 1, taskArray.get(index).toString()))
                .forEach(task -> tasks.append(task));

        // Remove trailing '\n' characters
        if (tasks.lastIndexOf("\n") != -1) {
            return tasks.substring(0, tasks.length() - 1);
        }
        return tasks.toString();
    }

    /**
     * Get all items in storage filtered by date
     *
     * @param date Date to filter
     *
     * @return String containing filtered items
     */
    public String getTasks(Instant date) throws InvalidArgumentException {
        StringBuilder tasks = new StringBuilder();
        try {
            int printIndex = 1;

            for (int i = 0; i < taskArray.size(); i++) {
                Task task = taskArray.get(i);

                // Check if date lies on deadline due date or within event start and end date
                if ((task instanceof Deadline && ((Deadline) task).isOn(date))
                        || (task instanceof Event && ((Event) task).encompasses(date))) {
                    tasks.append(String.format("%d.%s\n", printIndex, taskArray.get(i).toString()));
                    printIndex++;
                }
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new InvalidArgumentException(
                    "Date/time format is invalid. Please enter the date/time in the format 'YYYY/MM/DD'");
        }

        // Remove trailing '\n' characters
        if (tasks.lastIndexOf("\n") != -1) {
            return tasks.substring(0, tasks.length() - 1);
        }
        return tasks.toString();
    }

    /**
     * Get all items in storage filtered by keyword
     *
     * @param keyword Keyword to filter
     */
    public String getTasks(String keyword) {
        StringBuilder tasks = new StringBuilder();
        int printIndex = 1;

        for (int i = 0; i < taskArray.size(); i++) {
            Task task = taskArray.get(i);

            // Check if task description contains keyword
            if (task.descriptionContains(keyword)) {
                tasks.append(String.format("%d.%s\n", printIndex, taskArray.get(i).toString()));
                printIndex++;
            }
        }

        // Remove trailing '\n' characters
        if (tasks.lastIndexOf("\n") != -1) {
            return tasks.substring(0, tasks.length() - 1);
        }
        return tasks.toString();
    }

    /**
     * Save the current tasklist to file
     */
    public void saveTasks() throws StorageException {
        try {
            // Save to file
            JSONArray jsonArray = new JSONArray(taskArray);
            Storage.saveToFile(jsonArray, Duke.saveFile);
        } catch (IOException e) {
            throw new StorageException(String.format("Failed to save to file - %s", e.getMessage()));
        }
    }

    /**
     * Indicates whether some other object is "equal to" this TaskList
     *
     * @param obj Object to be checked against
     *
     * @return True if equal, False otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof TaskList) {
            TaskList taskList = (TaskList) obj;

            return this.taskArray.equals(taskList.taskArray);
        }

        return false;
    }
}
