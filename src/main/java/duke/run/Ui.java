package duke.run;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Read users input.
     *
     * @return String of users input.
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Prints welcome message.
     */
    public String greet() {
        return "--------------------------" + "\n"
                + "Welcome!! I'm Belle <3."
                + "\n" + "What can I do for you?"
                + "\n" + "--------------------------";
    }

    /**
     * Prints goodbye message.
     */
    public String bye() {
        this.sc.close();
        return "--------------------------"
                + "\n" + "Till next time!! Goodbye.";
    }

    public void printError(String msg) {
        System.out.println(msg);
    }
}

