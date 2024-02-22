package tool;

import java.util.ArrayList;

import task.Task;

/**
 * Represents the tool to deal with user interactions.
 */
public class Ui {
    private static final String POSSIBLE_COMMANDS = "1. Add a todo task\ntodo [task name]\ntd [task name]\n\n"
            + "2. Add a task with deadline\ndeadline [task name] /by [yyyy-mm-dd HH:MM]\n"
            + "dl [task name] /by [yyyy-mm-dd HH:MM]\n\n"
            + "3. Add an event\nevent [task name] /from [yyyy-mm-dd HH:MM] /to [yyyy-mm-dd HH:MM]\n"
            + "ev [task name] /from [yyyy-mm-dd HH:MM] /to [yyyy-mm-dd HH:MM]\n\n"
            + "4. List tasks\nlist\nls\n\n"
            + "5. Find task(s)\nfind [keyword(s)]\nf [keyword(s)]\n\n"
            + "6. Mark task\nmark [index]\nmk [index]\n\n"
            + "7. Unmark task\nunmark [index]\numk [index]\n\n"
            + "8. Delete task\ndelete [index]\nrm [index]\n\n"
            + "9. Exit program\nbye";

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
        message.add("Hello! I'm Chronos, your trusted task manager! ");
        message.add("What can I do for you today?\n\n");
        message.add("Not sure of the commands? Send me 'help' or 'sos' and I will show you the possible commands!");
        return String.join("", message);
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
        message.set(message.size() - 1, message.get(message.size() - 1).substring(0,
                message.get(message.size() - 1).length() - 1));
        return String.join(" ", message);
    }

    /**
     * Prints no outstanding tasks and list of commands.
     */
    public String printNoOutstandingTasks() {
        return "There are no outstanding tasks in your list.\n";
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
