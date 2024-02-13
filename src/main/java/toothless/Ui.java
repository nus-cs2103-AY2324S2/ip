package toothless;

import java.util.ArrayList;
import java.util.Scanner;
import toothless.tasks.*;

/**
 * This class is responsible for managing the user interface for the Toothless application.
 * It handles displaying messages to the user, including welcome and farewell messages, tasks,
 * and task lists, as well as reading user input.
 */
public class Ui {
    private String chatBotName = "Toothless";
    private String greetingString = "Hi! " + chatBotName + " is " + chatBotName + "!\n"
            + "What can " + chatBotName + " do for human?\n";
    private String exitString = "Bye. Hope to see you again soon!";

    /**
     * Displays the welcome message to the user.
     */
    public String showWelcome() {
        return greetingString;
    }

    /**
     * Displays the exit message to the user.
     */
    public String showFarewell() {
        return exitString;
    }

    /**
     * Displays a loading message indicating that previous tasks are being loaded from the file.
     */
    public String showLoadingTasks() {
        return "Loading previous recorded tasks...\n";
    }

    public String showDeletedTask(Task task, int size) {
        return "Noted. I've removed this task:\n" + task.toString() + "\nNow you have " + size + " tasks in the list.";
    }

    public String showAddedTask(Task task, int size) {
        return "Got it. I've added this task:\n" + task.toString() + "\nNow you have " + size + " tasks in the list.";
    }

    public String showMarkedTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    public String showUnmarkedTask(Task task) {
        return "Nice! I've marked this task as undone:\n" + task.toString();
    }

    public String showFoundTasks(ArrayList<Task> tasks) {
        String message = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            message += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return message;
    }

    public String showAllTasks(TaskList tasks) {
        String message = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            message += (i + 1) + ". " + tasks.getTask(i) + "\n";
        }
        return message;
    }

    /**
     * Displays all incomplete tasks to the user.
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public String showIncompleteTask(TaskList tasks) {
        String message = "You have these unmarked tasks:\n";
        System.out.println("You have these unmarked tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.getTask(i);
            if (!t.isDone()) {
                message += (i + 1) + ". " + t + "\n";
            }
        }
        return message;
    }
}
