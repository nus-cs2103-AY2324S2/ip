package ui;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * <p>
 *  Handles what the user interacts with. Contains the logic to scan User input as well as
 *  print output
 * </p>
 */
public class Ui {
    private static final String LINE = "____________________________________________________________\n";
    private final Scanner sc;
    private final PrintStream printer;
    /**
     * Initialises a UI class, setting up a Scanner and printer
     */
    public Ui() {
        this.sc = new Scanner(System.in);
        this.printer = new PrintStream(System.out);
    }
    /**
     * This method prints the welcome message on start up of chatBot
     */
    public void showWelcome() {
        printer.print(LINE + "Paws what you're doing! I'm Blawg\n"
                + "What can I do for you?\n" + LINE);
    }

    /**
     * This method prints the goodbye message on termination of chatBot
     */
    public String showBye() {
        return "Alright, this kitty's got to go chase some shadows. See you later!";
    }


    /**
     * This method prints the dividing line
     */
    public void showLine() {
        printer.print(LINE);
    }

    /**
     * This method gets User Input from CLI
     */
    public String getUserInput() {
        return sc.nextLine();
    }

    /**
     * This method outputs the result after ChatBot Processing
     * @Param result This wil be what is printed based on the Processing of data
     */
    public void showResult(String result) {
        printer.print(result);
    }

    /**
     * This method outputs any error that occurred in the programs
     * @Param result This will be the error printed
     */
    public void showError(String error) {
        printer.print(error);
    }

}
