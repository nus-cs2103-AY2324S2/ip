package toothless;

import java.util.ArrayList;

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
    private String exitString = "Toothless will miss you, human. Toothless will be here when you need Toothless.";

    /**
     * Displays the welcome message to the user.
     */
    public String showWelcome() {
        return greetingString;
    }

    public String showFarewell() {
        return exitString;
    }

    public String showLoadingTasks() {
        return "Loading previous human kept tasks...\n";
    }

    public String showDeletedTask(Task task, int size) {
        return "Toothless deletes this task:\n" + task.toString() + "\nHuman has " + size + " tasks now.";
    }

    public String showAddedTask(Task task, int size) {
        return "Toothless adds this task:\n" + task.toString() + "\nHuman has " + size + " tasks now.";
    }

    public String showMarkedTask(Task task) {
        return "Nice! Toothless have marked this task as done:\n" + task.toString();
    }

    public String showUnmarkedTask(Task task) {
        return "Nice! Toothless have marked this task as undone:\n" + task.toString();
    }

    public String showFoundTasks(ArrayList<Task> tasks) {
        String message = "Toothless have found these same tasks:\n";
        for (int i = 0; i < tasks.size(); i++) {
            message += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return message;
    }

    public String showAllTasks(TaskList tasks) {
        String message = "Here all human tasks:\n";
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
        String message = "Reminder\nHuman still have these tasks to do:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.getTask(i);
            if (!t.isDone()) {
                message += (i + 1) + ". " + t + "\n";
            }
        }
        return message;
    }

    public String showNoTaskNameWarning() {
        return "Human task no name :(";
    }

    public String showNoDeadlineWarning() {
        return "Human deadline no deadline @_@";
    }

    public String showEventNoStartWarning() {
        return "Human event no start date :/";
    }

    public String showEventNoEndWarning() {
        return "Human event no end date :/";
    }

    public String showEmptyListWarning() {
        return "Human list is empty like my tummy right now D:";
    }

    public String showNoMatchingTaskWarning() {
        return "No matching tasks in your list!";
    }

    public String showInvalidMarkWarning() {
        return "Human trying to mark nothing ^O^.\nFoolish";
    }

    public String showInvalidUnmarkWarning() {
        return "Human trying to unmark nothing ^O^.\nSilly";
    }

    public String showInvalidDeleteWarning() {
        return "Human trying to delete nothing ^O^.\nAbsurd";
    }
}
