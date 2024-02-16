package earl.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class responsible for interactions with the user.
 */
public class Ui {

    private static final String PADDING = " ".repeat(4);
    private static final String DIVIDER = "_".repeat(60);
    private static final String NEWLINE = System.lineSeparator();
    private static final String GREETING_MESSAGE =
            "Greetings, I, Earl, extend my esteemed salutations." + NEWLINE
            + PADDING + "Pray, how may I be of service to you"
                    + " on this auspicious day?";
    private static final String GOODBYE_MESSAGE =
            "Farewell, dear interlocutor!" + NEWLINE
            + PADDING + "Until our paths intertwine again, I bid you adieu.";

    private final Scanner sc;
    private final List<String> delayedResponse;
    private String[] response; // the lines of the previous response

    /** Class constructor. */
    public Ui() {
        sc = new Scanner(System.in);
        response = new String[1];
        response[0] = GREETING_MESSAGE;
        delayedResponse = new ArrayList<>();
    }

    private void setPrevResponse(String... arr) {
        response = arr;
    }

    /** Displays a horizontal divider for TUI mode. */
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
     * @param arr  a number of lines to print
     */
    public void makeResponse(String... arr) {
        setPrevResponse(arr);
        printDivider();
        printStrings(arr);
        printDivider();
    }

    /** Adds strings to be displayed later. */
    public void buildResponse(String... arr) {
        delayedResponse.addAll(List.of(arr));
    }

    /** Formats and prints the built response for the user to read. */
    public void completeResponse() {
        makeResponse(delayedResponse.toArray(String[]::new));
        delayedResponse.clear();
    }

    public String getUserInput() {
        return sc.nextLine();
    }

    /** Left pads the input string with the class's set padding. */
    public String leftPad(String line) {
        return PADDING + line;
    }

    public String appendNewline(String line) {
        return line + NEWLINE;
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
        int n = response.length;
        assert n > 0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n - 1; ++i) {
            res.append(PADDING).append(response[i]).append(NEWLINE);
        }
        res.append(PADDING).append(response[n - 1]); // last line has no newline
        return res.toString();
    }
}
