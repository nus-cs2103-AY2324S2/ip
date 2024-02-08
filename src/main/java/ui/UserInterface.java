package ui;

import tasks.Task;

import java.util.Scanner;

/**
 * Manages output to the command line
 */
public class UserInterface {
    private String openingMessage = "Hello! I'm Stille\n" + "What can I do for you?\n";
    private String closingMessage = "Bye. Hope to see you again soon!";
    private String dividerLine = "___________________________________";
    private String errorMessage = "Error: ";

    private Scanner sc;
    public UserInterface() {
        this.sc = new Scanner(System.in);
    }

    public void showMessage(String message) {
        showDividerLine();
        System.out.println(message);
        showDividerLine();
    }
    public void showOpeningMessage() {
        this.showMessage(openingMessage);
    }

    public void showClosingMessage() {
        this.showMessage(closingMessage);
    }

    public void showDividerLine() {
        System.out.println(dividerLine);
    }

    public void showError(Exception e) {
        this.showMessage(errorMessage + e.getMessage());
    }

    public void showList(String list) {
        this.showMessage("Here are the tasks in your list:\n" + list);
    }

    public void showMarkDone(Task task) {
        this.showMessage("Nice! I've marked this task as done:\n " + task);
    }

    public void showMarkNotDone(Task task) {
        this.showMessage("OK, I've marked this task as not done yet:\n " + task);
    }

    public void showDelete(Task task) {
        this.showMessage("Noted. I've removed this task:\n " + task);
    }

    public void showAdd(Task task) {
        this.showMessage("Got it. I've added this task:\n " + task);
    }

    public void showListSize(int size) {
        this.showMessage("Now you have " + size + " tasks in the list.");
    }

    public void showFound(String list) {
        this.showMessage("Here are the matching tasks in your list:\n" + list);
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
