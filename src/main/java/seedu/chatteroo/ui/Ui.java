package seedu.chatteroo.ui;

import seedu.chatteroo.tasks.Task;

/**
 * Represents the user interface of the Chatteroo ChatBot program.
 */
public class Ui {
    /**
     * Constructor for the Ui class.
     */
    public Ui() {
    }

    /**
     * Shows the welcome text.
     */
    public static String showWelcomeText() {
        return "Hello! I'm Chatteroo!\n" + "What can I do for you?";
    }

    /**
     * Shows the exit text.
     */
    public String showByeText() {
        return "Chatteroo says bye and hopes to see you again soon!\n";
    }

    /**
     * Shows the specified text when the list contains no tasks.
     */
    public void showNoTaskText() {
        System.out.println("There are no tasks in the list.\n");
    }

    public String showAddTaskText(Task newTask, int listCount) {
        String message = "Got it. I've added this task:\n" + newTask.toString() + "\n";
        message += ("Now you have " + listCount + " tasks in the list.\n");
        return message;
    }

    public String showDeleteTaskText(Task deletedTask, int listCount) {
        String message = "I've removed this task:\n" + deletedTask.toString() + "\n";
        message += ("Now you have " + listCount + " tasks in the list.\n");
        return message;
    }

    public String showFindTaskText(String keyword, int count) {
        String message = "";
        if (count == 0) {
            message = "There are no matching tasks in your list.\n";
        } else {
            message = "Here are the matching tasks in your list:\n";
        }
        return message;
    }
}
