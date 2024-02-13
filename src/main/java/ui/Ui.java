package ui;

import java.util.Scanner;

public class Ui {
    private Scanner in;
    private final String line = "____________________________________________________________";

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public void closeScanner() {
        this.in.close();
    }

    /**
     * Prints to the console a welcome message
     * everytime a user starts up the chatbot.
     */
    public void showWelcome() {
        System.out.println(line);
        System.out.println("Hello! I'm Fredricksen");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    /**
     * Returns the content String passed as argument
     * in between 2 lines given by the line variable.
     *
     * @param content The String in between the 2 lines.
     */
    public String output(String content) {
        return content + "\n";
    }

    /**
     * Prints to the console the error message of the Exception.
     */
    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Returns the next line that the user inputs into the CLI.
     * @return A String that the user inputs into the CLI.
     */
    public String readCommand() {
        return this.in.nextLine();
    }
}
