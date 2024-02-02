package BartenderBob;
import java.util.ArrayList;

public class Ui {
    public void leave() {
        System.out.println("Bye! Another round next time!");
    }
    public void showWelcomeMessage(String name) {
        System.out.println("Welcome back! I'm " + name + "\nHow's it going out there?");
    }
    public void showLoadingError() {
        System.out.println("Error occurred while loading!");
    }
    public void showInvalidInputError(String userInput) {
        System.out.println("What? I can't understand " + userInput + " =(");
    }
    public void showInvalidMarkCommand() {
        System.out.println("Hmm, please provide a valid task number to mark.");
    }
    public void showInvalidUnmarkCommand() {
        System.out.println("Hmm, please provide a valid task number to unmark.");
    }
    public void showInvalidDeleteCommand() {
        System.out.println("Hmm, please provide a valid task number to delete.");
    }
    public void showInvalidTodoCommand() {
        System.out.println("Hmm, did you add in a description for the todo?");
    }
    public void showInvalidDeadlineCommand() {
        System.out.println("Hey, this is an invalid format for deadline!");
    }
    public void showInvalidDateFormat() {
        System.out.println("Date format should be of the form yyyy-MM-dd!");
    }
    public void showInvalidEventCommand() {
        System.out.println("Hey, this is an invalid format for event!");
    }
    public void showOutOfBoundsCommand() {
        System.out.println("The index is out of bounds!");
    }

    public void showSaveChangesError() {
        System.out.println("Something went wrong while saving changes!");
    }
    public void showSaveTasksError() {
        System.out.println("Something went wrong while saving tasks!");
    }
    public void showStoreTasksMessage(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.show());
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }
    public void showListCommandHeader() {
        System.out.println("Here are the tasks in your list!");
    }
    public void showListElements(int number, ArrayList<Task> tasks, int i) {
        System.out.println(number + "." + tasks.get(i).show());
    }
    public void showMarkDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.show());
    }
    public void showUnmarkDone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.show());
    }
    public void showDelete(String display, int totalTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(display);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }
}
