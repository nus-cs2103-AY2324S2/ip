package duke.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

/**
 * The TaskManager class handles the management of tasks, including adding, deleting,
 * marking as complete or incomplete, and displaying tasks.
 */
public class TaskManager {
    public static final String INDENTATION = "      ";
    public static final String LINE = "    -----------------------------------------------------------------------------------------";
    TaskDisplay taskDisplay = new TaskDisplay();
    FileManager fileManager;

    List<Task> taskList;

    /**
     * Constructs a TaskManager with the specified username, initializing the FileManager
     * and loading saved tasks from the file.
     *
     * @param username The username associated with the task list.
     */
    public TaskManager(String username) {
        this.fileManager = new FileManager(username);
        this.taskList = new ArrayList<>();
        loadSavedTasks();
    }

    /**
     * Adds a new task to the task list based on the provided task description and type.
     * Automatically saves the updated task list.
     *
     * @param taskDescription The description of the task.
     * @param type            The type of the task (Todo, Event, Deadline).
     */
    public void addTask(String taskDescription, TaskType type) {
        try {
            if (taskDescription.trim().equalsIgnoreCase(type.toString())) {
                return;
            }

            Task task;
            switch (type) {
                case Todo:
                    task = new Todo(taskDescription, false);
                    break;
                case Event:
                    task = new Event(taskDescription, false, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
                    break;
                case Deadline:
                    LocalDateTime defaultDeadline = LocalDateTime.now().plusDays(1);
                    task = new Deadline(taskDescription, false, defaultDeadline);
                    break;
                default:
                    throw new DukeException("Hey, I'm not quite sure what that means. Mind giving me another shot at understanding?");
            }

            taskList.add(task);
            autoSaveTask();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
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
        System.out.println(LINE);
        try {
            if (taskList.isEmpty()) {
                System.out.println(INDENTATION + " No tasks to delete. Your duke.task list is already empty.");
                return;
            }

            taskList.clear();
            System.out.println(INDENTATION + " okay, noted. I've removed all tasks from the list.");
            autoSaveTask();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(LINE);
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
    public void displayTask(String input) {
        taskDisplay.displayTasks(taskList, input);
    }
}
