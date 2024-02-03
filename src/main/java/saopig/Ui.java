package saopig;

import java.util.Scanner;

/**
 * Represents the user interface of the program.
 */
public class Ui {
    private final String logo = " ____    _    ___  ____ ___ ____   ____   ___ _____ \n"
            + "/ ___|  / \\  / _ \\|  _ \\_ _/ ___| | __ ) / _ \\_   _|\n"
            + "\\___ \\ / _ \\| | | | |_) | | |  _  |  _ \\| | | || |  \n"
            + " ___) / ___ \\ |_| |  __/| | |_| | | |_) | |_| || |  \n"
            + "|____/_/   \\_\\___/|_|  |___\\____| |____/ \\___/ |_|\n";
    private Scanner scanner;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }
    /**
     * Prints the message.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints the message with lines.
     */
    private void printMessageWithLines(String message) {
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints goodbye message.
     */
    public void showGoodbye() {
        System.out.println("\n"
                + "As our time together comes to a close, "
                + "I just want to say it's been an absolute delight!\n "
                + "Remember, every day is a new adventure waiting to happen.\n "
                + "Bye for now, and take care! ");
        System.out.println(" ______   _______   _ \n"
                + "| __ ) \\ / / ____| | |\n"
                + "|  _ \\\\ V /|  _|   | |\n"
                + "| |_) || | | |___  |_|\n"
                + "|____/ |_| |_____| (_)");
    }
}
