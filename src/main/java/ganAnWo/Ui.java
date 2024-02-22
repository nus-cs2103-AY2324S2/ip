package ganAnWo;

import java.util.Scanner;

/**
 * Class used to handle all things relate to input and output.
 *
 */
public class Ui {
    private static String name = "GanAnWo";

    private Scanner sc;

    /**
     * Constructor for Ui.
     *
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints a welcome message.
     *
     */
    public void showWelcome() {
        System.out.println("Hello! I'm " + name + "\n"
                + "What can I do for you?");
    }

    /**
     * Prints a message based on the given string.
     *
     * @param m a string
     */
    public void showMessage(String m) {
        System.out.println(m);
    }

    /**
     * Prints a bye message.
     *
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Returns a string from the scanner.
     *
     * @return a string
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
