package arona.ui;

/**
 * Class to deal with the user interface.
 *
 * @author Maximilliano Utomo
 */
public class Ui {
    /**
     * Print multiple lines of output.
     * @param lines - the output
     */
    public static void printLines(String ...lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }

    /**
     * Prints a greeting for the user.
     */
    public static void helloUser() {
        printLines("Welcome, sensei! Arona has been waiting for you.");
    }

    /**
     * Prints a goodbye for the user.
     */
    public static void byeUser() {
        printLines("Thanks for the hard work, Sensei!");
    }
}
