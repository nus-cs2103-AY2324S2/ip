package axolotl.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Text UI of the application (to remove?)
 */
public class Ui {

    private static final String BORDER_DIVIDER = "================================";
    private static final String DIVIDER = "--------------------------------";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String readCommand() {
        String input = in.nextLine();

        return input;
    }

    public void showWelcome() {
        System.out.println("================================ \n" +
                "Hello I'm Axolotl! \n" +
                "What can I do for you? \n" +
                "================================ \n");
    }

    public static void showLoadingError() {
        System.out.println("-------------------------------- \n" +
                "Oops, error in uploading saved tasks. Please check if the format is correct. \n" +
                "-------------------------------- \n");
    }

    public static void showExit() {
        System.out.println("================================ \n" +
                "Bye. Hope to see you again soon! \n" +
                "================================ \n");
    }

}