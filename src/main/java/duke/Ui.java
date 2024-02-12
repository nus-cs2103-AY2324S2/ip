package duke;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import duke.task.Task;


/**
 * Represents the user interface of the Duke application.
 * This class handles interactions with the user, including displaying messages, errors, and task lists.
 */
public class Ui {

    private GuiObserver guiObserver;
    private Queue<String> messageHistory = new LinkedList<>(); // Stores recent messages


    /**
     * Sets the GuiObserver for this Ui instance.
     * The GuiObserver is used to display messages in the user interface.
     *
     * @param guiObserver The GuiObserver to be set.
     */
    public void setGuiObserver(GuiObserver guiObserver) {
        this.guiObserver = guiObserver;
    }

    /**
     * Displays a message to the user. If a GuiObserver is set, the message is displayed in the GUI.
     * Otherwise, it is printed to the console.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        if (guiObserver != null) {
            guiObserver.showMessage(message);
            messageHistory.add(message); // Capture the message
            while (messageHistory.size() > 10) { // Keep only the 10 most recent messages
                messageHistory.remove();
            }
        } else {
            System.out.println("GUI Observer not set: " + message);
        }
    }

    public String getLastMessage() {
        return messageHistory.peek();
    }


    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        showMessage("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        showMessage("Error: " + errorMessage);
    }

    /**
     * Displays a message indicating an error loading tasks from file.
     */
    public void showLoadingError() {
        showMessage("Error loading tasks from file. Starting with an empty task list.");
    }

    /**
     * Displays an exit message to the user.
     */
    public void showExitMessage() {
        showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        ArrayList<Task> taskArrayList = tasks.getTasks();
        if (taskArrayList.isEmpty()) {
            showMessage("The task list is empty.");
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < taskArrayList.size(); i++) {
                sb.append(" ").append(i + 1).append(". ").append(taskArrayList.get(i)).append("\n");
            }
            showMessage(sb.toString().trim());
        }
    }

    /**
     * Displays a list of tasks that match a search query to the user.
     *
     * @param tasks The list of tasks that match the search query.
     */
    public void showTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            showMessage("No matching tasks found.");
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(".").append(tasks.get(i)).append("\n");
            }
            showMessage(sb.toString().trim());
        }
    }

    /**
     * Displays the number of tasks in the task list to the user.
     *
     * @param tasks The TaskList whose size is to be displayed.
     */
    public void showNumTasks(TaskList tasks) {
        showMessage("Now you have " + tasks.getTasksSize() + " tasks in the list.");
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showMarkAsDoneMessage(Task task) {
        showMessage("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     * @return A message indicating the task has been marked as not done.
     */
    public String showMarkAsUndoneMessage(Task task) {
        return "Ok, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Displays a message indicating a task has been deleted from the task list.
     *
     * @param removedTask The task that has been deleted.
     * @param tasks The updated TaskList after the task has been deleted.
     */
    public void showDeleteMessage(Task removedTask, TaskList tasks) {
        showMessage("Noted. I've removed this task:\n" + removedTask.toString());
        showNumTasks(tasks);
    }

    /**
     * Displays a message indicating a task has been added to the task list.
     *
     * @param newTask The task that has been added.
     * @param tasks The updated TaskList after the task has been deleted.
     */
    public void showAddTaskMessage(Task newTask, TaskList tasks) {
        showMessage("Got it. I've added this task:\n " + newTask.toString());
        showNumTasks(tasks);
    }
}
