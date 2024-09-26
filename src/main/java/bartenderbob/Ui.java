package bartenderbob;
import java.util.ArrayList;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    /**
     * Creates an instance of the Ui class.
     */
    public String leave() {
        return "Bye! Another round next time!";
    }
    /**
     * Returns the welcome message to the user.
     *
     * @param name Name of the user.
     * @return Welcome message to the user.
     */
    public String showWelcomeMessage(String name) {
        return "Welcome back! I'm " + name + "\nHow's it going out there?";
    }
    /**
     * Returns the message to the user when an error occurred while loading.
     *
     * @return Error message to the user.
     */
    public String showLoadingError() {
        return "Error occurred while loading!";
    }
    /**
     * Returns the message to the user when an error occurred in the user input.
     *
     * @return Error message to the user.
     */
    public String showInvalidInputError(String userInput) {
        return "What? I can't understand " + userInput + " =(";
    }
    /**
     * Returns the message to the user when an error occurred in the mark command.
     *
     * @return Error message to the user.
     */
    public String showInvalidMarkCommand() {
        return "Hmm, please provide a valid task number to mark.";
    }
    /**
     * Returns the message to the user when an error occurred in the unmark command.
     *
     * @return Error message to the user.
     */
    public String showInvalidUnmarkCommand() {
        return "Hmm, please provide a valid task number to unmark.";
    }
    /**
     * Returns the message to the user when an error occurred in the delete command.
     *
     * @return Error message to the user.
     */
    public String showInvalidDeleteCommand() {
        return "Hmm, please provide a valid task number to delete.";
    }
    /**
     * Returns the message to the user when an error occurred in the todo command.
     *
     * @return Error message to the user.
     */
    public String showInvalidTodoCommand() {
        return "Hmm, did you add in a description for the todo?";
    }
    /**
     * Returns the message to the user when an error occurred in the deadline command.
     *
     * @return Error message to the user.
     */
    public String showInvalidDeadlineCommand() {
        return "Hey, this is an invalid format for deadline!";
    }
    /**
     * Returns the message to the user when the input date is of the wrong format.
     *
     * @return Error message to the user.
     */
    public String showInvalidDateFormat() {
        return "Check whether the date format is yyyy-MM-dd or if the dates make sense!";
    }
    /**
     * Returns the message to the user when an error occurred in the event command.
     *
     * @return Error message to the user.
     */
    public String showInvalidEventCommand() {
        return "Hey, this is an invalid format for event!";
    }
    /**
     * Returns the message to the user when the user specified index is out of bounds.
     *
     * @return Error message to the user.
     */
    public String showOutOfBoundsCommand() {
        return "The index is out of bounds!";
    }
    /**
     * Returns the message to the user when an error occurred while saving changes to the storage.
     *
     * @return Error message to the user.
     */
    public String showSaveChangesError() {
        return "Something went wrong while saving changes!";
    }
    /**
     * Returns the message to the user when an error occurred while saving tasks to the storage.
     *
     * @return Error message to the user.
     */
    public String showSaveTasksError() {
        return "Something went wrong while saving tasks!";
    }
    /**
     * Returns the message to the user when the task is stored in the storage.
     *
     * @return Message to the user.
     */
    public String showStoreTasksMessage(Task task, int totalTasks) {
        return "Got it. I've added this task:\n" + task.show() + "\nNow you have " + totalTasks
                + " tasks in the list.";
    }
    /**
     * Returns the header message to the user when the list of tasks is shown.
     *
     * @return Message to the user.
     */
    public String showListCommandHeader() {
        return "Here are the tasks in your list!";
    }
    /**
     * Displays the list of tasks to the user.
     *
     * @return Message to the user.
     */
    public String showListElements(int number, ArrayList<Task> tasks, int i) {
        return number + "." + tasks.get(i).show();
    }
    /**
     * Returns the message to the user when the task is marked as done.
     *
     * @return Message to the user.
     */
    public String showMarkDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task.show();
    }
    /**
     * Returns the message to the user when the task is unmarked.
     *
     * @return Message to the user.
     */
    public String showUnmarkDone(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.show();
    }
    /**
     * Returns the message to the user when the task is deleted.
     *
     * @return Message to the user.
     */
    public String showDelete(String display, int totalTasks) {
        return "Noted. I've removed this task:\n" + display
                + "\nNow you have " + totalTasks + " tasks in the list.";
    }
    /**
     * Returns the header message to the user when the task is found.
     *
     * @return Message to the user.
     */
    public String showFindCommandHeader() {
        return "Here are the matching tasks in your list:";
    }
    /**
     * Returns the message to the user when an event date clashes with an existing event.
     *
     * @return Error message to the user.
     */
    public String showClashError(Event newEvent, Event existingEvent) {
        return "Hey, this event clashes with another event!\n" + newEvent.show()
                + "\nClashes with existing event:\n" + existingEvent.show();
    }
}
