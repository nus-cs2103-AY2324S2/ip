package duke;

/**
 * Ui Class to handle any output to user
 */
public class Ui {
    public static final String DIVIDER = "________________________________________";
    public Ui() {

    }

    public String showWelcome() {
        return DIVIDER + "\nHello! I'm Homie" + "What can I do for you?\n" + DIVIDER;
    }

    public String showGoodbye() {
        return DIVIDER + "\nBye Homie. Hope to see you again soon!\n" + DIVIDER;
    }

    public String showDivider() {
        return DIVIDER + "\n";
    }

    public String showLoadingError() {
        return DIVIDER + "\nHomie, theres an error loading your tasks!\n" + DIVIDER;
    }

    public String showListMessage() {
        return "Here are the tasks in your list:\n";
    }

    /**
     * Show delete message after deleting a task
     * @param task The task object to be deleted
     * @param tasks The task lists that stores all the task objects
     * @return String message to acknowledge the task has been deleted
     */
    public String showDeleteMessage(Task task, TaskList tasks) {
        return DIVIDER + "\nNoted. I've removed this task:\n" + task.toString()
                + "Now you have " + tasks.getSize() + " tasks in the list.\n" + DIVIDER;
    }

    /**
     * Show message after adding a to-do task
     * @param task The task object to be added
     * @param tasks The task lists that stores all the task objects
     * @return String message to acknowledge that the to-do task has been added
     */
    public String showToDoMessage(Task task, TaskList tasks) {
        return DIVIDER + "\nGot it. I've added this task:\n" + task.toString()
                + "\nNow you have " + tasks.getSize() + " tasks in the list.\n" + DIVIDER;
    }

    /**
     * Show message after adding a deadline task
     * @param task The deadline task to be added
     * @param tasks The task lists that stores all the task objects
     * @return String message to acknowledge that the deadline task has been added
     */
    public String showDeadlineMessage(Task task, TaskList tasks) {
        return DIVIDER + "\nGot it. I've added this task:\n" + task.toString()
                + "\nNow you have " + tasks.getSize() + " tasks in the list.\n" + DIVIDER;
    }

    /**
     * Show message after added an event task
     * @param task The event task to be added
     * @param tasks The task lists that stores all the tasks object
     * @return String message to acknowledge that the event task has been added
     */
    public String showEventMessage(Task task, TaskList tasks) {
        return DIVIDER + "\nGot it. I've added this task:\n" + task.toString()
                + "\nNow you have " + tasks.getSize() + " tasks in the list.\n" + DIVIDER;
    }

    public String showMarkMessage(Task task) {
        return DIVIDER + "\nNice! I've marked this task as done:\n" + task.toString() + "\n" + DIVIDER;
    }

    public String showUnmarkMessage(Task task) {
        return DIVIDER + "Ok, I've marked this task as not done yet:" + task.toString() + "\n" + DIVIDER;
    }

    public String showWrongCommand() {
        return DIVIDER + "\nWrong Command!\n" + DIVIDER;
    }

    public String showFindMessage() {
        return "Here are the matching tasks in your list:";
    }
}
