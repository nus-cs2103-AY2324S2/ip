package drew.ui;

import java.util.Scanner;

/**
 * This class handles all the interactions with the user.
 * This includes printing replies and reading input.
 */
public class Ui {
    public static final String DELIMITER = "______________________________________";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * The first message to be printed after load is complete.
     */
    public static String greetUser() {
        return "Hello! I'm drew!\n What can I do for you?";
    }

    /**
     * The last message to be printed before saving.
     */
    public void bidFarewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Encloses input string with lines.
     * @param input String to be enclosed in lines
     */
    public void reply(String input) {
        System.out.println(DELIMITER);
        System.out.println(input);
        System.out.println(DELIMITER);
    }

    public String readInput() {
        return sc.nextLine();
    }

}
