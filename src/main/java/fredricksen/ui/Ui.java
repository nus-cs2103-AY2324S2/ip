package fredricksen.ui;

import java.util.Scanner;

/**
 * Represents an "Ui" class that handles the User Interface of the chatbot,
 * focus on displaying some outputs, or pass messages to be displayed.
 */
public class Ui {
    private Scanner in;

    /**
     * Constructs an Ui object which initializes a Scanner object to read user inputs.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * A function to close the Scanner.
     */
    public void closeScanner() {
        this.in.close();
    }

    /**
     * returns a welcome message everytime a user starts up the chatbot.
     */
    public String showWelcome() {
        return "Wassup! I'm Fredricksen\n" + "What can I do for you?";
    }

    /**
     * Returns the content String passed as argument
     *
     * @param content The String passed as an argument.
     */
    public String output(String content) {
        return content;
    }

    /**
     * Prints to the console the error message of the Exception.
     */
    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Returns the next line that the user inputs into the CLI.
     *
     * @return A String that the user inputs into the CLI.
     */
    public String readCommand() {
        return this.in.nextLine();
    }
}
