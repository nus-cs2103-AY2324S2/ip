package hanxiao;

import java.util.Scanner;

/**
 * Class for user interaction.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor for ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Print out welcome message
     */
    public void showWelcome() {
        System.out.println("    Hello! I'm Hanxiao.\n  What can I do for you?");
    }

    /**
     * Read in the user input
     * @return
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Print out the reply message.
     * @param reply Reply message.
     */
    public void printReply(String reply) {
        System.out.print(reply);
    }

    /**
     * Print out the Good byr message
     */
    public String sayGoodbye() {
        return "    Bye, Hope to see you again soon!\n";
    }
}
