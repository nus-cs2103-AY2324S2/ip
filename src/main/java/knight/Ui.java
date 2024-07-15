package knight;

/**
 * Controls the user interface of the program.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    static final String LOGO = "┓┏┓  •  ┓  \n"
            + "┃┫ ┏┓┓┏┓┣┓╋\n"
            + "┛┗┛┛┗┗┗┫┛┗┗\n"
            + "       ┛\n";

    /**
     * Prints text with a line above and below it, as well as an indent.
     *
     * @param message The message to be printed.
     */
    static void speak(String message) {
        System.out.println(LINE);
        System.out.println(message.replaceAll("(?m)^", "    "));
        System.out.println(LINE);
    }
}
