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
    public String greet() {
        return "Hello, I am Snomnom \n what can I do for you nomnomnom";
    }

    /**
     * Says goodbye to the user when the bot has finished running.
     */
    public String bye() {
        return "B-bye, hope to see you soon nomnomnom";
    }

    /**
     * Prints out a confirmation message when a task is added successfully.
     * @param task is the name of the task.
     */
    private String echo(String task) {
        return "added: " + task + "nomnomnom";
    }


}
