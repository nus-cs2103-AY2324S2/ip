package duke.task;

import duke.task.exception.DukeException;

import java.time.LocalDateTime;
import java.util.*;

/**
 * The TaskManager class handles the management of tasks, including adding, deleting,
 * marking as complete or incomplete, and displaying tasks.
 */
public class TaskManager {
    private FileManager fileManager;
    private List<Task> taskList;

    public TaskManager(String username) {
        this.fileManager = new FileManager(username);
        this.taskList = new ArrayList<>();
        loadSavedTasks();
    }

    /**
     * Adds a new task to the task list and returns the index of the added task.
     *
     * @param taskDescription The description of the task.
     * @param type            The type of the task (Todo, Event, Deadline).
     * @return The index of the newly added task, or -1 if the task could not be added.
     */
    public int addTask(String taskDescription, TaskType type) {
        Task task = createTask(taskDescription, type);
        if (task != null) {
            taskList.add(task);
            autoSaveTask();
            return taskList.size() - 1;
        }
        return -1;
    }

    private Task createTask(String taskDescription, TaskType type) {
        switch (type) {
            case TODO:
                return new Todo(taskDescription, false);
            case EVENT:
                return new Event(taskDescription, false, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
            case DEADLINE:
                LocalDateTime defaultDeadline = LocalDateTime.now().plusDays(1);
                return new Deadline(taskDescription, false, defaultDeadline);
            default:
                return null;
        }
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(taskList);
    }

    public Task getTask(int index) {
        if (index >= 0 && index < taskList.size()) {
            return taskList.get(index);
        }
        return null;
    }

    /**
     * Finds tasks containing the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return A list of tasks containing the specified keyword in their descriptions.
     */
    public List<Task> findTask(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getTaskDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

    /**
     * Deletes the task at the specified index from the task list.
     * Automatically saves the updated task list.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        try {
            if (index < 0 || index >= taskList.size()) {
                return;
            }
            taskList.remove(index);
            autoSaveTask();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes all tasks from the task list.
     * Automatically saves the updated task list.
     */
    public void deleteAllTasks() {
        try {
            if (taskList.isEmpty()) {
                return;
            }

            taskList.clear();
            autoSaveTask();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Marks the task at the specified index as complete.
     *
     * @param index The index of the task to mark as complete.
     */
    public void markAsComplete(int index) {
        if (index >= 0 && index < taskList.size()) {
            Task task = taskList.get(index);
            task.markAsComplete();
        }
    }

    /**
     * Marks the task at the specified index as incomplete.
     *
     * @param index The index of the task to mark as incomplete.
     */
    public void markAsIncomplete(int index) {
        if (index >= 0 && index < taskList.size()) {
            Task task = taskList.get(index);
            task.markAsIncomplete();
        }
    }

    /**
     * Loads saved tasks from file.
     */
    private void loadSavedTasks() {
        taskList = fileManager.loadTasks(taskList);
    }

    /**
     * Automatically saves tasks to file.
     */
    public void autoSaveTask() {
        fileManager.saveTasks(taskList);
    }

}
