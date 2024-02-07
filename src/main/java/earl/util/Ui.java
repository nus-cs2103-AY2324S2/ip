package earl.util;

import java.util.Scanner;

/**
 * Class responsible for interactions with the user.
 */
public class Ui {

    private static final String PADDING = " ".repeat(4);
    private static final String DIVIDER = "_".repeat(60);

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
        prevResponse = arr;
        printDivider();
        for (String s : arr) {
            System.out.println(PADDING + s);
        }
        printDivider();
    }

    public String getUserInput() {
        return sc.nextLine();
    }

    public void showGreeting() {
        makeResponse("Hello! I'm Earl", "What can I do for you?");
    }

    public void showGoodbye() {
        makeResponse("Goodbye! See you soon.");
    }

    public String getResponse() {
        StringBuilder res = new StringBuilder(PADDING + DIVIDER + "\n");
        for (String line: prevResponse) {
            res.append(PADDING).append(line).append("\n");
        }
        res.append(PADDING).append(DIVIDER).append("\n");
        return res.toString();
    }
}
