package felix.utils;

import java.util.Scanner;

public class Ui {
    private static final String BOT_NAME = "Felix";
    private static final String LOGO = "___________    .__  .__        \n"
            + "\\_   _____/___ |  | |__|__  ___\n"
            + " |    __)/ __ \\|  | |  \\  \\/  / \n"
            + " |     \\\\  ___/|  |_|  |>    <  \n"
            + " \\___  / \\___  >____/__/__/\\_ \\ \n"
            + "     \\/      \\/              \\/ \n";
    private static final String LINE_SEPARATOR = "__________________________"
            + "__________________________________";

    private final Scanner scanner;

    /**
     * Constructor for Ui class
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints String representation of object.
     */
    public void println(Object obj) {
        System.out.println(obj);
    }

    /**
     * Prints horizontal line separator of length 60.
     */
    public void printHorizontalLine() {
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints Exception message.
     */
    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints message to be displayed and corresponding Exception if an error occurs when loading from hard disk file.
     */
    public void printLoadingError(Exception e) {
        System.out.println("Error occurred when loading file:");
        this.printException(e);
        this.printHorizontalLine();
    }

    /**
     * Prints chatbot logo.
     */
    public void printLogo() {
        System.out.println("Hello from\n" + LOGO);
        this.printHorizontalLine();
    }

    /**
     * Prints chatbot introduction.
     */
    public void printIntroduction() {
        this.printHorizontalLine();
        System.out.printf("Hello! I'm %s\nWhat can I do for you?\n",BOT_NAME);
        this.printHorizontalLine();
    }

    /**
     * Prints goodbye message.
     */
    public void exitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Returns next line from Scanner object.
     */
    public String getNextLine() {
        return this.scanner.nextLine();
    }
}
