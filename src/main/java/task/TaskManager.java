package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class TaskManager {
    public static final String INDENTATION = "      ";
    public static final String LINE = "    -----------------------------------------------------------------------------------------";
    TaskDisplay taskDisplay = new TaskDisplay();
    FileManager fileManager;

    List<Task> taskList;

    public TaskManager(String username) {
        this.fileManager = new FileManager(username);
        this.taskList = new ArrayList<>();
        loadSavedTasks();
    }

    public void addTask(String taskDescription, TaskType type) {
        try {
            if (taskDescription.trim().equalsIgnoreCase(type.toString())) {
                return;
            }

            Task task;
            switch (type) {
                case TODO:
                    task = new Todo(taskDescription, false);
                    break;
                case EVENT:
                    task = new Event(taskDescription, false, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
                    break;
                case DEADLINE:
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

    public void deleteAllTasks() {
        System.out.println(LINE);
        try {
            if (taskList.isEmpty()) {
                System.out.println(INDENTATION + " No tasks to delete. Your task list is already empty.");
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

    public void markAsComplete(int index) {
        if (index >= 0 && index < taskList.size()) {
            Task task = taskList.get(index);
            task.markAsComplete();
        }
    }

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

    public void displayTask(String input) {
        taskDisplay.displayTasks(taskList, input);
    }
}
