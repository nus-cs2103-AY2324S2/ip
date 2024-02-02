package Ui;

import java.util.Scanner;

public class Ui {
    Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Greets the user when the bot is started.
     */
    private void greet() {
        System.out.println("Hello, I'm Snom");
        System.out.println("What can I do for you?");
    }

    /**
     * Says goodbye to the user when the bot has finished running.
     */
    private void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out a confirmation message when a task is added successfully.
     * @param task is the name of the task.
     */
    private void echo(String task) {
        System.out.println("added: " + task);
    }

    public String getCommand() {
        String cmd = this.input.nextLine();
        return cmd;
    }
}
