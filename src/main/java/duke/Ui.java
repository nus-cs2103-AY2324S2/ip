package duke;

import duke.TaskList;

import java.util.ArrayList;

public class Ui {
    public static final String divider = "____________________________________________________________";
    public Ui() {

    }

    public void showWelcome() {
        System.out.println(divider + "\nHello! I'm Homie");
        System.out.println("What can I do for you?\n" + divider);
    }

    public void showGoodbye() {
        System.out.println(divider + "\nBye Homie. Hope to see you again soon!\n" + divider);
    }

    public void showDivider() {
        System.out.println(divider + "\n");
    }

    public void showLoadingError() {
        System.out.println(divider + "\nHomie, theres an error loading your tasks!\n" + divider);
    }

    public void showListMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    public void showDeleteMessage(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");


    }

    public void showToDoMessage(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    public void showDeadlineMessage(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    public void showEventMessage(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    public void showMarkMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showUnmarkMessage(Task task) {
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void showWrongCommand() {
        System.out.println("Wrong Command!");
    }

}
