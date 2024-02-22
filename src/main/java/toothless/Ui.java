package toothless;

import java.util.ArrayList;

import toothless.tasks.*;

/**
 * Represents the user interface of the chatbot.
 * This class is responsible for displaying messages to the user.
 */
public class Ui {
    private String chatBotName = "Toothless";
    private String greetingString = "Hi! " + chatBotName + " is " + chatBotName + "!\n"
            + "What can " + chatBotName + " do for human?\n";
    private String exitString = "Toothless will miss you, human.\nToothless will be here when you need Toothless.";

    public String showWelcome() {
        return greetingString;
    }

    public String showFarewell() {
        return exitString;
    }

    public String showLoadingTasks() {
        return "Loading previous human kept tasks...\n";
    }

    // Task messages
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

    // List messages
    /**
     * Displays the tasks found by the find command to the user.
     * @param tasks The list of tasks found by the find command.
     */
    public String showFoundTasks(ArrayList<Task> tasks) {
        String message = "Toothless have found these same tasks:\n";
        for (int i = 0; i < tasks.size(); i++) {
            message += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return message;
    }

    /**
     * Displays all tasks to the user.
     * @param tasks The TaskList containing the tasks to be displayed.
     */
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

    // Warning messages
    public String showNoTaskNameWarning() {
        return "Task has no name! >_<\n" +
                "Please enter in this format: <task_type> <task_description> [/<date>]";
    }

    public String showNoDeadlineWarning() {
        return "Deadline has no date! >_<\n" +
                "Human, please enter a date for the deadline in this format: yyyy-MM-dd hh:mm\n" +
                "with a /by in front of the date.";
    }

    public String showEventNoStartWarning() {
        return "Event has no start date! >_<\n" +
                "Human, please enter a start date for the event in this format: yyyy-MM-dd hh:mm\n" +
                "with a /from in front of the date.";
    }

    public String showEventNoEndWarning() {
        return "Event has no end date! >_<\n" +
                "Human, please enter an end date for the event in this format: yyyy-MM-dd hh:mm\n" +
                "with a /to in front of the date.";
    }

    public String showEmptyListWarning() {
        return "Human list is empty like my tummy right now! :(" +
                "Please add some tasks to me! :D";
    }

    public String showNoMatchingTaskWarning() {
        return "Toothless cannot find any matching tasks! :(";
    }

    public String showInvalidMarkWarning() {
        return "Human trying to mark nothing ^O^. Foolish.\n" +
                "Please enter a valid task number to mark. :)";
    }

    public String showInvalidUnmarkWarning() {
        return "Human trying to unmark nothing ^O^. Silly.\n" +
                "Please enter a valid task number to unmark. :)";
    }

    public String showInvalidDeleteWarning() {
        return "Human trying to delete nothing ^O^. Absurd.\n" +
                "Please enter a valid task number to delete. :)";
    }
}
