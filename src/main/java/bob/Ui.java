package bob;

/**
 * A class that deals with the interactions with the user.
 */
public class Ui {
    /**
     * An empty constructor since there is nothing to initialise.
     */
    public Ui() {
        // Do nothing
    }

    /**
     * A method to greet the user.
     *
     * @return A string representing the output.
     */
    public String showGreetMessage() {
        String greet = "Hello! I'm Bob.\n"
                + "What can I do for you?\n";

        return greet;
    }

    /**
     * A method to respond to the user when prompted for 'list' command.
     *
     * @return A string representing the output.
     */
    public String showTasksInListMessage() {
        return "Here are the tasks in your list:\n";
    }

    /**
     * A method to respond to the user when prompted for 'clear' command.
     *
     * @return A string representing the output.
     */
    public String showClearMessage() {
        return "Your tasks have been cleared.";
    }

    /**
     * A method to respond when the user has an incomplete entry.
     *
     * @return A string representing the output.
     */
    public String showIncompleteEntryMessage() {
        return "Your entry is incomplete!";
    }

    /**
     * A method that indicates that a task has been marked as done.
     *
     * @param task The task we want to mark.
     * @return A string representing the output.
     */
    public String showMarkTaskMessage(Task task) {
        return "Nice! I've marked this task as done:\n"
                + "  " + task;
    }

    /**
     * A method that indicates that a task has been unmarked.
     *
     * @param task The task we want to unmark.
     * @return A string representing the output.
     */
    public String showUnmarkTaskMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + task;
    }

    /**
     * A method to indicate that no such task exists.
     *
     * @return A string representing the output.
     */
    public String showNoSuchTaskMessage() {
        return "There exists no such task.";
    }

    /**
     * A method that prints a message when a deadline is added.
     *
     * @param task The deadline task we want to add.
     * @param size The size of the task list.
     * @return A string representing the output.
     */
    public String showDeadlineMessage(Task task, int size) {
        return "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * A method that prints a message when a todo is added.
     *
     * @param task The todo task we want to add.
     * @param size The size of the task list.
     * @return A string representing the output.
     */
    public String showTodoMessage(Task task, int size) {
        return "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * A method that prints a message when an event is added.
     *
     * @param task The event task we want to add.
     * @param size The size of the task list.
     * @return A string representing the output.
     */
    public String showEventMessage(Task task, int size) {
        return "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * A method that prints a message when a task is deleted.
     *
     * @param task The task we want to delete.
     * @param size The size of the taskList.
     * @return A string representing the output.
     */
    public String showDeleteMessage(Task task, int size) {
        return "Noted. I've removed this task:\n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * A method that shows a message when the command is unknown.
     *
     * @return A string representing the output.
     */
    public String showUnknownCommandMessage() {
        return "Bob knows not what that means.";
    }

    /**
     * A method that prints an error message when the date/time input is in the wrong format.
     *
     * @return A string representing the output.
     */
    public String showDateTimeFormatErrorMessage() {
        return "Your date/time input format should be yyyy-MM-dd HHmm";
    }

    /**
     * A method to display an exit message when exiting the chatbot.
     *
     * @return A string representing the output.
     */
    public String showExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * A method to display a message that no tasks were found from 'find'.
     *
     * @return A string representing the output.
     */
    public String showNoMatchingTasksMessage() {
        return "No tasks were found containing the specified keyword.";
    }

    /**
     * A method to display a message for the tasks found in the task list from 'find'.
     *
     * @return A string representing the output.
     */
    public String showMatchingTasksInListMessage() {
        return "Here are the matching tasks in your list:\n";
    }
}
