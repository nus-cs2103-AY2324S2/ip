package earl.util;

import java.util.Scanner;

/**
 * Class responsible for interactions with the user.
 */
public class Ui {

    private static final String PADDING = " ".repeat(4);
    private static final String DIVIDER = "_".repeat(60);
    private static final String GREETING_MESSAGE = "Hello! I'm Earl\n"
            + PADDING + "What can I do for you?";

    private static final String GOODBYE_MESSAGE = "Goodbye! See you soon.";

    private final Scanner sc;

    private String[] prevResponse;

    /** Class constructor. */
    public Ui() {
        sc = new Scanner(System.in);
        prevResponse = new String[0];
    }

    /** Displays horizontal divider. */
    private static void printDivider() {
        System.out.println(PADDING + DIVIDER);
    }

    /** Prints each element of input on a new line. */
    private static void printStrings(String... arr) {
        for (String s : arr) {
            System.out.println(PADDING + s);
        }
    }

    /**
     * Formats and prints the arguments for the user to read.
     * <p>
     * Each argument is automatically put on a new line. A padding
     * of 4 spaces is also added automatically to give the user a
     * better sense of which text is outputted by Earl.
     *
     * @param arr  a vararg of strings to print
     */
    public void makeResponse(String... arr) {
        setPrevResponse(arr);
        printDivider();
        printStrings(arr);
        printDivider();
    }

    private void setPrevResponse(String... arr) {
        prevResponse = arr;
    }

    public String getUserInput() {
        return sc.nextLine();
    }

    public void showGreeting() {
        makeResponse(GREETING_MESSAGE);
    }

    public void showGoodbye() {
        makeResponse(GOODBYE_MESSAGE);
    }

    /**
     * Returns the previous response made for GUI interactions.
     *
     * @return  the previous response appearing as it would on a
     *          text based UI
     */
    public String getResponse() {
        StringBuilder res = new StringBuilder(PADDING + DIVIDER + "\n");
        for (String line: prevResponse) {
            res.append(PADDING).append(line).append("\n");
        }
        res.append(PADDING).append(DIVIDER).append("\n");
        return res.toString();
    }
}
