package univus;

/**
 * The Ui class that deals with interactions with the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";

    /**
     * Prints a greeting to the user.
     */
    public void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Univus");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * prints a bye to the user.
     */
    public void bye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }


}
