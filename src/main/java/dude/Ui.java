package dude;

/**
 * Ui class that handles printing for Dude.
 */
public class Ui {
    static final String SPACER = "____________________________________________________________\n";

    /**
     * Prints text with spacers.
     * @param text Text to be printed.
     */
    public static void print(String text) {
        System.out.println(SPACER + text + SPACER);
    }

    /**
     * Prints greeting text.
     */
    public static String greeting() {
        return "Hello! I'm Dude\nWhat can I do for you?\n";
    }

    /**
     * Prints goodbye text.
     */
    public static String goodbye() {
        return "Bye. Hope to see you again soon!\n";
    }
}
