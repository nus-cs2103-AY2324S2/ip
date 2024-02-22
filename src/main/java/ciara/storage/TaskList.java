package ciara.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;

import ciara.Ciara;
import ciara.exceptions.InvalidArgumentException;
import ciara.exceptions.MissingTaskException;
import ciara.exceptions.StorageException;
import ciara.exceptions.TaskCorruptedException;
import ciara.exceptions.TaskNotSupportedException;
import ciara.ui.Cli;

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
     * Array used to store archived tasks for the application
     */
    private ArrayList<Task> archivedTaskArray = new ArrayList<>();

    /**
     * Create a new task list with values loaded from a file
     *
     * @param file File to load tasks from
     */
    public TaskList(File file) {
        try {
            Storage.loadFromFile(this, file);
        } catch (TaskNotSupportedException | TaskCorruptedException | FileNotFoundException | StorageException e) {
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
     * @param task       Task to be added
     * @param isArchived Visibility of the task
     */
    public void addTask(Task task, boolean isArchived) throws StorageException {
        if (isArchived) {
            archivedTaskArray.add(task);
        } else {
            taskArray.add(task);
        }

        // Save to file
        saveTasks();
    }

    /**
     * Remove an item from task list
     *
     * @param deleteIndex Index of the item to delete
     * @param isArchived  Visibility of the task
     *
     * @return Deleted task
     */
    public Task deleteTask(int deleteIndex, boolean isArchived) throws StorageException, MissingTaskException {
        try {
            Task deletedTask;
            if (isArchived) {
                deletedTask = archivedTaskArray.remove(deleteIndex);
            } else {
                deletedTask = taskArray.remove(deleteIndex);
            }

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
     * @param isArchived Visibility of the tasks to retrieve
     *
     * @return String containing all items in storage
     */
    public String getTasks(boolean isArchived) {
        ArrayList<Task> taskList;
        if (isArchived) {
            taskList = archivedTaskArray;
        } else {
            taskList = taskArray;
        }

        return getTasksString(taskList);
    }

    /**
     * Get all items in storage filtered by date
     *
     * @param date       Date to filter
     * @param isArchived Visibility of the tasks
     *
     * @return String containing filtered items
     */
    public String getTasks(Instant date, boolean isArchived) throws InvalidArgumentException {
        try {
            List<Task> filteredTasks;
            if (isArchived) {
                filteredTasks = archivedTaskArray.stream()
                        .filter(task -> {
                            boolean validDeadline = task instanceof Deadline && ((Deadline) task).isOn(date);
                            boolean validEvent = task instanceof Event && ((Event) task).encompasses(date);

                            return validDeadline || validEvent;
                        })
                        .collect(Collectors.toList());
            } else {
                filteredTasks = taskArray.stream()
                        .filter(task -> {
                            boolean validDeadline = task instanceof Deadline && ((Deadline) task).isOn(date);
                            boolean validEvent = task instanceof Event && ((Event) task).encompasses(date);

                            return validDeadline || validEvent;
                        })
                        .collect(Collectors.toList());
            }

            return getTasksString(filteredTasks);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new InvalidArgumentException(
                    "Date/time format is invalid. Please enter the date/time in the format 'YYYY/MM/DD'");
        }
    }

    /**
     * Get all items in storage filtered by keyword
     *
     * @param keyword    Keyword to filter
     * @param isArchived Visibility of the tasks
     *
     */
    public String getTasks(String keyword, boolean isArchived) {
        List<Task> filteredTasks;
        if (isArchived) {
            filteredTasks = archivedTaskArray.stream()
                    .filter(task -> task.descriptionContains(keyword))
                    .collect(Collectors.toList());
        } else {
            filteredTasks = taskArray.stream()
                    .filter(task -> task.descriptionContains(keyword))
                    .collect(Collectors.toList());
        }

        return getTasksString(filteredTasks);
    }

    /**
     * Archive/unarchive a task
     *
     * @param taskIndex Index of the item to archive/unarchive
     * @param toArchive Visibility to apply to the task
     *
     * @return Task succesfully archived/unarchived
     */
    public Task archiveTask(int taskIndex, boolean toArchive) throws StorageException, MissingTaskException {
        try {
            Task task;
            if (toArchive) { // Archive task
                task = taskArray.get(taskIndex);

                // Remove task from taskArray
                this.deleteTask(taskIndex, false);

                // Add task to archived array
                task.archive();
                this.addTask(task, true);
            } else { // Unarchive task
                task = archivedTaskArray.get(taskIndex);

                // Remove task from archived array
                this.deleteTask(taskIndex, true);

                // Add task to taskArray
                task.unarchive();
                this.addTask(task, false);
            }

            // Save to file
            saveTasks();

            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new MissingTaskException("Task does not exist");
        }
    }

    /**
     * Save the current tasklist to file
     */
    public void saveTasks() throws StorageException {
        try {
            // Save to file
            JSONArray jsonArray = new JSONArray(taskArray);
            jsonArray.putAll(new JSONArray(archivedTaskArray));
            Storage.saveToFile(jsonArray, Ciara.getSaveFile());
        } catch (IOException e) {
            throw new StorageException(String.format("Failed to save to file - %s", e.getMessage()));
        }
    }

    /**
     * Get string representation of tasks
     *
     * @param taskList Task list whose string representation to get
     *
     * @return String representation of the specified tasklist
     */
    private String getTasksString(List<Task> taskList) {
        StringBuilder tasks = new StringBuilder();

        IntStream
                .range(0, taskList.size())
                .mapToObj(index -> String.format("%d.%s\n", index + 1, taskList.get(index).toString()))
                .forEach(task -> tasks.append(task));

        // Remove trailing '\n' characters
        if (tasks.lastIndexOf("\n") != -1) {
            return tasks.substring(0, tasks.length() - 1);
        }

        return tasks.toString();
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
