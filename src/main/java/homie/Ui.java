package homie;

/**
 * Ui Class to handle outputs to user.
 */
public class Ui {
    public Ui() {

    }

    /**
     * Gets welcome message when user first open application.
     *
     * @return Welcome message in String.
     */
    public String getWelcomeMessage() {
        return "Sup I'm Homie!!\n" + "What can I do for you?";
    }

    /**
     * Gets goodbye message when user exits the application.
     *
     * @return Goodbye message in String.
     */
    public String getGoodbyeMessage() {
        return "Bye Homie. Hope to see you again soon!";
    }

    /**
     * Gets loading error message when there is an error loading tasks from storage.
     *
     * @return Loading error message in String.
     */
    public String getLoadingErrorMessage() {
        return "Homie, theres an error loading your tasks!";
    }

    /**
     * Shows all the tasks in the task lists.
     *
     * @return String message to show all tasks in the list.
     */
    public String showListMessage(String allTasks) {
        return "Here are the tasks in your list:\n" + allTasks;
    }

    /**
     * Gets delete message after deleting a task.
     *
     * @param task The task object to be deleted.
     * @param taskListSize The remaining number of tasks in the task list.
     * @return Delete task message in String.
     */
    public String getDeleteMessage(Task task, int taskListSize) {
        return "Noted. I've removed this task:\n" + "\t" + task.toString()
                + "\nNow you have " + taskListSize + " tasks in the list.";
    }

    /**
     * Gets message after adding a to-do task.
     *
     * @param task The new to-do task to be added.
     * @param taskListSize The remaining number of tasks in the task list.
     * @return String message to acknowledge that the to-do task has been added.
     */
    public String getToDoMessage(Task task, int taskListSize) {
        return "Got it. I've added this task:\n" + "\t" + task.toString()
                + "\nNow you have " + taskListSize + " tasks in the list.";
    }

    /**
     * Gets message after adding a deadline task.
     *
     * @param task The deadline task to be added.
     * @param taskListSize The remaining number of tasks in the task list.
     * @return String message to acknowledge that the deadline task has been added.
     */
    public String getDeadlineMessage(Task task, int taskListSize) {
        return "Got it. I've added this task:\n" + "\t" + task.toString()
                + "\nNow you have " + taskListSize + " tasks in the list.\n";
    }

    /**
     * Gets message after adding an event task.
     *
     * @param task The event task to be added.
     * @param taskListSize The remaining number of tasks in the task list.
     * @return String message to acknowledge that the event task has been added.
     */
    public String getEventMessage(Task task, int taskListSize) {
        return "Got it. I've added this task:\n" + "\t" + task.toString()
                + "\nNow you have " + taskListSize + " tasks in the list.";
    }

    /**
     * Gets message after marking a task as done.
     *
     * @param task The task to be marked as done.
     * @return String message to acknowledge task has been marked.
     */
    public String getMarkMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + "\t" + task.toString();
    }

    /**
     * Gets message after marking a task as not done.
     *
     * @param task The task to be marked as no done.
     * @return String message to acknowledge task has been marked as not done.
     */
    public String getUnmarkMessage(Task task) {
        return "Ok, I've marked this task as not done yet:\n" + "\t" + task.toString();
    }

    /**
     * Gets all tasks that contains the matching keyword.
     *
     * @return String message of all tasks with matching keyword.
     */
    public String showFindMessage(String matchingTasks) {
        return "Here are the matching tasks in your list:\n" + matchingTasks;
    }
}
