package duke;

import java.util.List;

import duke.task.Task;

/**
 * Represents the user interface of the Duke application.
 * This class handles interactions with the user, including displaying messages, errors, and task lists.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     */
    public String showWelcome() {
        return "Hello! I'm Jamie\nWhat can I do for you?";
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public String showError(String errorMessage) {
        return "Error: " + errorMessage;
    }

    /**
     * Displays an exit message to the user.
     */
    public String showExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public String showTaskList(TaskList tasks) {
        List<Task> taskArrayList = tasks.getTasks();
        if (taskArrayList.isEmpty()) {
            return "The task list is empty.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < taskArrayList.size(); i++) {
                sb.append(" ").append(i + 1).append(". ").append(taskArrayList.get(i)).append("\n");
            }
            return sb.toString().trim();
        }
    }

    /**
     * Displays a list of tasks that match a search query to the user.
     *
     * @param tasks The list of tasks that match the search query.
     */
    public String showTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            return sb.toString().trim();
        }
    }

    /**
     * Displays the number of tasks in the task list to the user.
     *
     * @param tasks The TaskList whose size is to be displayed.
     */
    public String showNumTasks(TaskList tasks) {
        return "Now you have " + tasks.getTasksSize() + " tasks in the list.";
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public String showMarkAsDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Displays a message indicating a task has been deleted from the task list.
     *
     * @param removedTask The task that has been deleted.
     * @param tasks The updated TaskList after the task has been deleted.
     */
    public String showDeleteMessage(Task removedTask, TaskList tasks) {
        String deleteTaskMessage = "Noted. I've removed this task:\n" + removedTask;
        String numOfTasksMessage = showNumTasks(tasks);
        return deleteTaskMessage + "\n" + numOfTasksMessage;
    }

    /**
     * Displays a message indicating a task has been added to the task list.
     *
     * @param newTask The task that has been added.
     * @param tasks The updated TaskList after the task has been added.
     */
    public String showAddTaskMessage(Task newTask, TaskList tasks) {
        String addTaskMessage = "Got it. I've added this task:\n" + newTask;
        String numOfTasksMessage = showNumTasks(tasks);
        return addTaskMessage + "\n" + numOfTasksMessage;
    }

    public String showEditTaskMessage(Task task) {
        return "Task updated: " + task;
    }

}
