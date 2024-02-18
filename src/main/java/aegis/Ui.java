package aegis;

import java.util.Scanner;

/**
 * Ui class contains methods that get user input and print outputs.
 */
public class Ui {

    /**
     * Constructor for creating Ui object.
     */
    public Ui() {}

    /**
     * Prints the logo of the Aegis assistance program.
     */
    public void printLogo() {
        String logo = "   _____                 __        \n" +
                "  /  _  \\   ____   ____ |__| ______\n" +
                " /  /_\\  \\_/ __ \\ / ___\\|  |/  ___/\n" +
                "/    |    \\  ___// /_/  |  |\\___ \\ \n" +
                "\\____|____/\\___  \\___  /|__/______/\n" +
                "                \\_____/             ";
        System.out.println(logo + "\n");
    }

    /**
     * Prints Aegis assistant greeting message.
     */
    public void printGreeting() {
        System.out.println("Greetings! I am Aegis.\n"
                + "How can I assist you?\n");
    }

    /**
     * Prints Aegis assistance farewell message.
     */
    public void printFarewell() {
        System.out.println("Goodbye! Have a pleasant day!\n");
    }

    /**
     * Prints a series of dashes that act as a divider.
     */
    public void printDivider() {
        String divider = "-----------------------------------------------\n";
        System.out.println(divider);
    }

    /**
     * Returns a String that is entered into the command line interface by the user.
     *
     * @return String entered by user.
     */
    public String getUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints a message indicating an error locating file.
     */
    public void printFileNotFoundError() {
        System.out.println("An error has occurred locating the file.");
    }

    /**
     * Prints a message indicating an IO error.
     */
    public void printIoException() {
        System.out.println("An error has occurred editing the file.");
    }

    /**
     * Prints a message indicating a task has been successfully marked as completed.
     */
    public void printMarkTaskSuccess() {
        System.out.println("Well done, task marked as completed.\n");
    }

    /**
     * Prints a message indicating a task has been successfully marked as not completed.
     */
    public void printUnmarkTaskSuccess() {
        System.out.println("Understood, task marked as uncompleted.\n");
    }

    /**
     * Prints a message indicating a task has been successfully created.
     */
    public void printCreateTaskSuccess() {
        System.out.println("Confirmed. New task added:\n");
    }

    /**
     * Prints a message indicating a task has been successfully deleted.
     */
    public void printDeleteTaskSuccess() {
        System.out.println("Acknowledged. The following task has been removed:\n");
    }
}
