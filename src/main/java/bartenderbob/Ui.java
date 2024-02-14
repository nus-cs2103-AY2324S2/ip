package bartenderbob;
import java.util.ArrayList;

public class Ui {
    public String leave() {
        return "Bye! Another round next time!";
    }
    public String showWelcomeMessage(String name) {
        return "Welcome back! I'm " + name + "\nHow's it going out there?";
    }
    public String showLoadingError() {
        return "Error occurred while loading!";
    }
    public String showInvalidInputError(String userInput) {
        return "What? I can't understand " + userInput + " =(";
    }
    public String showInvalidMarkCommand() {
        return "Hmm, please provide a valid task number to mark.";
    }
    public String showInvalidUnmarkCommand() {
        return "Hmm, please provide a valid task number to unmark.";
    }
    public String showInvalidDeleteCommand() {
        return "Hmm, please provide a valid task number to delete.";
    }
    public String showInvalidTodoCommand() {
        return "Hmm, did you add in a description for the todo?";
    }
    public String showInvalidDeadlineCommand() {
        return "Hey, this is an invalid format for deadline!";
    }
    public String showInvalidDateFormat() {
        return "Date format should be of the form yyyy-MM-dd!";
    }
    public String showInvalidEventCommand() {
        return "Hey, this is an invalid format for event!";
    }
    public String showOutOfBoundsCommand() {
        return "The index is out of bounds!";
    }

    public String showSaveChangesError() {
        return "Something went wrong while saving changes!";
    }
    public String showSaveTasksError() {
        return "Something went wrong while saving tasks!";
    }
    public String showStoreTasksMessage(Task task, int totalTasks) {
        return "Got it. I've added this task:\n" + task.show() + "\nNow you have " + totalTasks
                + " tasks in the list.";
    }
    public String showListCommandHeader() {
        return "Here are the tasks in your list!";
    }
    public String showListElements(int number, ArrayList<Task> tasks, int i) {
        return number + "." + tasks.get(i).show();
    }
    public String showMarkDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task.show();
    }
    public String showUnmarkDone(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.show();
    }
    public String showDelete(String display, int totalTasks) {
        return "Noted. I've removed this task:\n" + display
                + "\nNow you have " + totalTasks + " tasks in the list.";
    }
    public String showFindCommandHeader() {
        return "Here are the matching tasks in your list:";
    }
    public String showClashError(Event newEvent, Event existingEvent) {
        return "Hey, this event clashes with another event!\n" + newEvent.show()
                + "\nClashes with existing event:\n" + existingEvent.show();
    }
}
