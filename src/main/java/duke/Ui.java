package duke;

import java.util.ArrayList;

public class Ui {
    public void showWelcomeMessage() {
        printSeparator();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printSeparator();
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }

    public void showTaskAdded(Task task, int taskCount) {
        printSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printSeparator();
    }

    public void showTaskDeleted(Task task, int taskCount) {
        printSeparator();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printSeparator();
    }

    public void showTaskList(TaskList tasks) throws DukeException {
        printSeparator();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
        printSeparator();
    }

    public void showMarkTask(Task task) {
        printSeparator();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        printSeparator();
    }

    public void showUnmarkTask(Task task) {
        printSeparator();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        printSeparator();
    }

    public void showError(String message) {
        printSeparator();
        System.out.println("ERROR: " + message);
        printSeparator();
    }

    public void printSeparator() {
        System.out.println("____________________________________________________________");
    }
}
