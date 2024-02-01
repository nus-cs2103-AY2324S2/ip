package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the user interface of the Duke chatbot.
 */
public class Ui {
    /**
     * Displays a welcome message.
     */
        public void showWelcome() {

            System.out.println("Hello! I'm Aether!");

            System.out.println("What can I do for you?");

        }
    /**
     * Displays an error message for failed task loading.
     */
        public void showLoadingError() {
            System.out.println("Error loading tasks from file.");
        }

    /**
     * Displays a separator line.
     */
    public void showSeparator() {
        System.out.println("_____________________________");
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to display.
     */
        public void showErrorMessage(String errorMessage) {
            System.out.println("OOPS!!! " + errorMessage);
            System.out.println("_____________________________");
        }

    /**
     * Displays the list of tasks.
     *
     * @param taskList The task list to display.
     */
    public void showTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        List<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println("_____________________________");
    }


    /**
     * Displays a message indicating a task has been added.
     *
     * @param task       The added task.
     * @param taskCount  The total number of tasks after adding.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("_____________________________"); 
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param deletedTask      The deleted task.
     * @param remainingTasks   The remaining number of tasks after deletion.
     */
    public void showTaskDeleted(Task deletedTask, int remainingTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + deletedTask);
        System.out.println("Now you have " + remainingTasks + " tasks in the list.");
        System.out.println("_____________________________");
    }

    /**
     * Displays a message for an invalid task index.
     */
    public void showInvalidTaskIndex() {
        System.out.println("Invalid task index. Please provide a valid task index.");
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param markedTask The marked task.
     */
    public void showTaskMarked(Task markedTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + markedTask);
        System.out.println("_____________________________");
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @param unmarkedTask The unmarked task.
     */
    public void showTaskUnmarked(Task unmarkedTask) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + unmarkedTask);
        System.out.println("_____________________________");
    }

    /**
     * Displays a goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Goodbye. Hope to see you again soon!");
        System.out.println("_____________________________");
    }

}
