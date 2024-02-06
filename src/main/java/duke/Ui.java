package duke;

public class Ui {
    public static final String DIVIDER = "____________________________________________________________";
    public Ui() {

    }

    public void showWelcome() {
        System.out.println(DIVIDER + "\nHello! I'm Homie");
        System.out.println("What can I do for you?\n" + DIVIDER);
    }

    public void showGoodbye() {
        System.out.println(DIVIDER + "\nBye Homie. Hope to see you again soon!\n" + DIVIDER);
    }

    public void showDivider() {
        System.out.println(DIVIDER + "\n");
    }

    public void showLoadingError() {
        System.out.println(DIVIDER + "\nHomie, theres an error loading your tasks!\n" + DIVIDER);
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

    public void showFindMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }
}
