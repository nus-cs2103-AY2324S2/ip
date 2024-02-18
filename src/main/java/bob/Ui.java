package bob;

/*
 * A class that deals with the interactions with the user.
 */
public class Ui {
    /*
     * An empty constructor since there is nothing to initialise.
     */
    public Ui() {
        // Do nothing
    }

    /*
     * A method to greet the user.
     */
    public void showGreetMessage() {
        String greet = " Hello! I'm Bob.\n"
                + " What can I do for you?\n";

        System.out.println(greet);
    }

    /*
     * A method to respond to the user when prompted for 'list' command.
     */
    public void showTasksInListMessage() {
        System.out.println(" Here are the tasks in your list:");
    }

    /*
     * A method to respond to the user when prompted for 'clear' command.
     */
    public void showClearMessage() {
        System.out.println(" Your tasks have been cleared.");
    }

    /*
     * A method to respond when the user has an incomplete entry.
     */
    public void showIncompleteEntryMessage() {
        System.out.println(" Your entry is incomplete!");
    }

    /*
     * A method that indicates that a task has been marked as done.
     */
    public void showMarkTaskMessage(Task task) {
        System.out.println(" Nice! I've marked this task as done:\n"
                + "  " + task);
    }

    /*
     * A method that indicates that a task has been unmarked.
     */
    public void showUnmarkTaskMessage(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:\n"
                + "  " + task);
    }

    /*
     * A method to indicate that no such task exists.
     */
    public void showNoSuchTaskMessage() {
        System.out.println(" There exists no such task.");
    }

    /*
     * A method that prints a message when a deadline is added.
     */
    public void showDeadlineMessage(Task task, int size) {
        System.out.println(" Got it. I've added this task:\n"
                + "  " + task + "\n"
                + " Now you have " + size + " tasks in the list.");
    }

    /*
     * A method that prints a message when a todo is added.
     */
    public void showTodoMessage(Task task, int size) {
        System.out.println(" Got it. I've added this task:\n"
                + "  " + task + "\n"
                + " Now you have " + size + " tasks in the list.");
    }

    /*
     * A method that prints a message when an event is added.
     */
    public void showEventMessage(Task task, int size) {
        System.out.println(" Got it. I've added this task:\n"
                + "  " + task + "\n"
                + " Now you have " + size + " tasks in the list.");
    }

    /*
     * A method that prints a message when a task is deleted.
     */
    public void showDeleteMessage(Task task, int size) {
        System.out.println(" Noted. I've removed this task:\n"
                + "  " + task + "\n"
                + " Now you have " + size + " tasks in the list.");
    }

    /*
     * A method that shows a message when the command is unknown.
     */
    public void showUnknownCommandMessage() {
        System.out.println(" Bob knows not what that means.");
    }

    /*
     * A method that prints an error message when the date/time input is in the wrong format.
     */
    public void showDateTimeFormatErrorMessage() {
        System.out.println("Your date/time input format should be yyyy-MM-dd HHmm");
    }

    /*
     * A method to display an exit message when exiting the chatbot.
     */
    public void showExitMessage() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    /*
     * A method to display a message that no tasks were found from 'find'.
     */
    public void showNoMatchingTasksMessage() {
        System.out.println("No tasks were found containing the specified keyword.");
    }

    /*
     * A method to display a message for the tasks found in the task list from 'find'.
     */
    public void showMatchingTasksInListMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }
}
