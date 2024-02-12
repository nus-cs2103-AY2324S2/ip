package snomui;

import java.util.Scanner;

/**
 * The Ui implements the user interface
 * of the SnomBot.
 */
public class Ui {
    private Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Greets the user when the bot is started.
     */
    public void greet() {
        System.out.println("Hello, I'm Snom");
        System.out.println("What can I do for you?");
    }

    /**
     * Says goodbye to the user when the bot has finished running.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out a confirmation message when a task is added successfully.
     * @param task is the name of the task.
     */
    private void echo(String task) {
        System.out.println("added: " + task);
    }

    /**
     * Gets the command entered by the user.
     * @return a String representing the command of
     *         the user.
     */
    public String getCommand() {
        String cmd = this.input.nextLine();
        return cmd;
    }
}
