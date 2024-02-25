package ben.ui;

import ben.tasks.Task;
import ben.tasks.TaskList;
import ben.tasks.Todo;

import java.util.List;

public class Ui {
    public static final String ERROR_MESSAGE = "ERROR: ";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String LIST_MESSAGE = "Here are the tasks in your list:";
    public static final String MARKED_TASK_MESSAGE = "Nice! I've marked this task as done:";
    public static final String UNMARKED_TASK_MESSAGE = "OK, I've marked this task as not done yet:";
    public static final String ADDED_TASK_MESSAGE = "Got it. I've added this task:";
    public static final String TASK_SIZE_PRE_MESSAGE = "Now you have ";
    public static final String TASK_SIZE_POST_MESSAGE = " tasks in the list.";
    public static final String DELETED_TASK_MESSAGE = "Noted. I've removed this task:";
    public static final String TASKS_FOUND_MESSAGE = "Here are the matching tasks in your list:";
    public static final String NO_TASKS_FOUND_MESSAGE = "Sorry, there are no matching tasks... Try another keyword!";

    public String show(String... messages) {
        StringBuilder output = new StringBuilder();
        for (String message : messages) {
            output.append(message)
                    .append("\n");
        }
        return output.toString();
    }

    public String showError(String message) {
        return show(ERROR_MESSAGE,
                    message);
    }

    public String showExit() {
        return show(EXIT_MESSAGE);
    }

    public String showList(TaskList tasks) {
        return show(LIST_MESSAGE,
                    showTaskList(tasks));
    }

    public String showTask(Task task) {
        return show(task.toString());
    }

    public String showTask(TaskList tasks, int index) {
        return show(tasks.toString(index));
    }

    public String showTaskList(TaskList tasks) {
        return tasks.showTaskList();
    }

    public String showMarkedTask(TaskList tasks, int index) {
        return show(MARKED_TASK_MESSAGE,
                    showTask(tasks, index));
    }

    public String showUnmarkedTask(TaskList tasks, int index) {
        return show(UNMARKED_TASK_MESSAGE,
                    showTask(tasks, index));
    }

    public String showTasksSize(TaskList tasks) {
        return show(TASK_SIZE_PRE_MESSAGE +
                    tasks.size() +
                    TASK_SIZE_POST_MESSAGE);
    }

    public String showAddedTask(TaskList tasks, Task task) {
        return show(ADDED_TASK_MESSAGE,
                    showTask(task),
                    showTasksSize(tasks));
    }

    public String showDeletedTask(Task task) {
        return show(DELETED_TASK_MESSAGE,
                    showTask(task));
    }

    public String showTasksFound(TaskList tasks) {
        return show(TASKS_FOUND_MESSAGE,
                    tasks.showTaskList());
    }

    public String showNoTasksFound() {
        return show(NO_TASKS_FOUND_MESSAGE);
    }
}
