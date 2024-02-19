package duke.task;

import duke.task.exception.DukeException;

import java.time.LocalDateTime;
import java.util.*;

/**
 * The TaskManager class handles the management of tasks, including adding, deleting,
 * marking as complete or incomplete, and displaying tasks.
 */
public class TaskManager {
    private TaskDisplay taskDisplay;
    private FileManager fileManager;

    private List<Task> taskList;

    /**
     * Constructs a TaskManager with the specified username, initializing the FileManager
     * and loading saved tasks from the file.
     *
     * @param username The username associated with the task list.
     */
    public TaskManager(String username) {
        this.fileManager = new FileManager(username);
        taskDisplay = new TaskDisplay();
        this.taskList = new ArrayList<>();
        loadSavedTasks();
    }

    /**
     * Adds a new task to the task list based on the provided task description and type.
     * Automatically saves the updated task list. Prevents adding a task if one with the
     * same description and type already exists.
     *
     * @param taskDescription The description of the task.
     * @param type            The type of the task (Todo, Event, Deadline).
     * @return
     */
    public boolean addTask(String taskDescription, TaskType type) {
        try {
            if (taskDescription.trim().equalsIgnoreCase(type.toString())) {
                return false;
            }

            Task task;
            switch (type) {
                case TODO:
                    task = new Todo(taskDescription, false);
                    break;
                case EVENT:
                    task = new Event(taskDescription, false,
                            LocalDateTime.now(), LocalDateTime.now().plusDays(1));
                    break;
                case DEADLINE:
                    LocalDateTime defaultDeadline = LocalDateTime.now().plusDays(1);
                    task = new Deadline(taskDescription, false, defaultDeadline);
                    break;
                default:
                    throw new DukeException("Hey, I'm not quite sure what"
                            + " that means. Mind giving me another shot at understanding?");
            }

            taskList.add(task);
            autoSaveTask();
            return true;
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return false;
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

    private void loadSavedTasks() {
        taskList = fileManager.loadTasks(taskList);
    }

    public void autoSaveTask() {
        fileManager.saveTasks(taskList);
    }

    /**
     * Displays tasks based on the user input command using the TaskDisplay instance.
     *
     * @param input The user input command.
     */
    public String displayTask(String input) {
        return taskDisplay.displayTasks(taskList, input);
    }
}
