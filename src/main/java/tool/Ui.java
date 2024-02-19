package tool;

import java.util.ArrayList;

import task.Task;

/**
 * Represents the tool to deal with user interactions.
 */
public class Ui {
    private static final String POSSIBLE_COMMANDS = "TODO     --- todo [task name]\n"
            + "DEADLINE --- deadline [task name] /by [yyyy-mm-dd HH:MM]\n"
            + "EVENT    --- event [task name] /from [yyyy-mm-dd HH:MM] /to [yyyy-mm-dd HH:MM]";

    /**
     * Constructs a Ui object.
     */
    public Ui() {
    }

    /**
     * Prints greetings to user.
     */
    public static String greetUser() {
        ArrayList<String> message = new ArrayList<>();
        message.add("Hello! I'm Chronos.\n");
        message.add("What can I do for you?");
        return String.join(" ", message);
    }

    /**
     * Prints goodbye to user.
     */
    public static String bidGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints task list.
     *
     * @param tasks List of tasks.
     */
    public String printTasks(TaskList tasks) {
        ArrayList<String> message = new ArrayList<>();
        message.add("Here are the tasks in your list:\n");
        for (int i = 1; i < tasks.size() + 1; i++) {
            task.Task currentTask = tasks.getTask(i - 1);
            message.add(i + ". " + currentTask.toString() + "\n");
        }
        return String.join(" ", message);
    }

    /**
     * Prints filtered task list.
     *
     * @param tasks List of tasks with the specific keyword.
     */
    public String printFilteredTasks(TaskList tasks) {
        ArrayList<String> message = new ArrayList<>();
        message.add("Here are the matching tasks in your list:\n");
        for (int i = 1; i < tasks.size() + 1; i++) {
            task.Task currentTask = tasks.getTask(i - 1);
            message.add(i + ". " + currentTask.toString() + "\n");
        }
        return String.join(" ", message);
    }

    /**
     * Prints no outstanding tasks and list of commands.
     */
    public String printNoOutstandingTasks() {
        return "There are no outstanding tasks in your list.\n" + "You may add various tasks with the commands below:\n"
                + POSSIBLE_COMMANDS;
    }

    /**
     * Prints list of commands.
     */
    public static String printHelp() {
        return "Here are the possible commands:\n" + POSSIBLE_COMMANDS;
    }

    /**
     * Prints success message of adding a todo task.
     *
     * @param todo Todo object added.
     * @param noOfTasks Number of tasks in the list.
     */
    public String printAddTodoSuccessful(Task todo, int noOfTasks) {
        ArrayList<String> message = new ArrayList<>();
        message.add("Got it. I've added this task:\n");
        message.add(todo + "\n");
        message.add("Now you have " + noOfTasks + " tasks in the list.");
        return String.join(" ", message);
    }

    /**
     * Prints success message of adding a deadline.
     *
     * @param deadline Deadline object added.
     * @param noOfTasks Number of tasks in the list.
     */
    public String printAddDeadlineSuccessful(Task deadline, int noOfTasks) {
        ArrayList<String> message = new ArrayList<>();
        message.add("Got it. I've added this task:\n");
        message.add(deadline + "\n");
        message.add("Now you have " + noOfTasks + " tasks in the list.");
        return String.join(" ", message);
    }

    /**
     * Prints success message of adding an event.
     *
     * @param event Event object added.
     * @param noOfTasks Number of tasks in the list.
     */
    public String printAddEventSuccessful(Task event, int noOfTasks) {
        ArrayList<String> message = new ArrayList<>();
        message.add("Got it. I've added this task:\n");
        message.add(event + "\n");
        message.add("Now you have " + noOfTasks + " tasks in the list.");
        return String.join(" ", message);
    }

    /**
     * Prints success message of marking a task as completed.
     *
     * @param selectedTaskToBeMarked Selected task to be marked as completed.
     */
    public String printMarkTaskSuccessful(Task selectedTaskToBeMarked) {
        ArrayList<String> message = new ArrayList<>();
        message.add("OK, I've marked this task as not done yet:\n");
        message.add(selectedTaskToBeMarked.toString());
        return String.join(" ", message);
    }

    /**
     * Prints success message of unmarking a task as incomplete.
     *
     * @param selectedTaskToBeUnmarked Selected task to be marked as completed.
     */
    public String printUnmarkTaskSuccessful(Task selectedTaskToBeUnmarked) {
        ArrayList<String> message = new ArrayList<>();
        message.add("OK, I've marked this task as not done yet:\n");
        message.add(selectedTaskToBeUnmarked.toString());
        return String.join(" ", message);
    }

    /**
     * Prints success message of deleting a task.
     *
     * @param deletedTask Selected task to be deleted.
     * @param noOfTasks Number of tasks in the list.
     */
    public String printDeleteTaskSuccessful(Task deletedTask, int noOfTasks) {
        ArrayList<String> message = new ArrayList<>();
        message.add("Noted. I've removed this task:\n");
        message.add(deletedTask + "\n");
        message.add("Now you have " + noOfTasks + " tasks in the list.");
        return String.join(" ", message);
    }

    /**
     * Prints custom error message for NumberFormatException.
     */
    public String printNumberFormatException() {
        return "Task number is not an integer. Please include a valid task number.";
    }

    /**
     * Prints custom error message for IndexOutOfBoundsException.
     */
    public String printIndexOutOfBoundsException() {
        return "Task number out of range. Please include a valid task number.";
    }
}
