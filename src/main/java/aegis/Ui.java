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
     * Returns the logo of the Aegis assistance program.
     */
    public String getLogo() {
        String logo = "   _____                 __        \n" +
                "  /  _  \\   ____   ____ |__| ______\n" +
                " /  /_\\  \\_/ __ \\ / ___\\|  |/  ___/\n" +
                "/    |    \\  ___// /_/  |  |\\___ \\ \n" +
                "\\____|____/\\___  \\___  /|__/______/\n" +
                "                \\_____/             ";
        return logo;
    }

    /**
     * Prints Aegis assistant greeting message.
     */
    public void printGreeting() {
        System.out.println("Greetings! I am Aegis.\n"
                + "How can I assist you?\n");
    }

    /**
     * Returns Aegis assistant greeting message.
     */
    public String getGreeting() {
        String message = "Greetings! I am Aegis.\n"
                + "How can I assist you?";
        return message;
    }

    /**
     * Prints Aegis assistance farewell message.
     */
    public void printFarewell() {
        System.out.println("Goodbye! Have a pleasant day!\n");
    }

    /**
     * Returns Aegis assistance farewell message
     */
    public String getFarewell() {
        return "Goodbye! Have a pleasant day!";
    }

    /**
     * Prints a series of dashes that act as a divider.
     */
    public void printDivider() {
        String divider = "-----------------------------------------------\n";
        System.out.println(divider);
    }

    /**
     * Returns a series of dashes that act as a divider.
     */
    public String getDivider() {
        return "-----------------------------------------------\n";
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
     * Returns a message indicating an error locating file.
     */
    public String getFileNotFoundError() {
        return "An error has occurred locating the file.";
    }

    /**
     * Prints a message indicating an IO error.
     */
    public void printIoException() {
        System.out.println("An error has occurred editing the file.");
    }

    /**
     * Returns a message indicating an IO error.
     */
    public String getIoException() {
        return "An error has occurred editing the file.";
    }

    /**
     * Prints a message indicating a task has been successfully marked as completed.
     */
    public void printMarkTaskSuccess() {
        System.out.println("Well done, task marked as completed.\n");
    }

    /**
     * Returns a message indicating a task has been successfully marked as completed.
     */
    public String getMarkTaskSuccess() {
        return "Well done, task marked as completed.\n";
    }

    /**
     * Prints a message indicating a task has been successfully marked as not completed.
     */
    public void printUnmarkTaskSuccess() {
        System.out.println("Understood, task marked as uncompleted.\n");
    }

    /**
     * Returns a message indicating a task has been successfully marked as not completed.
     */
    public String getUnmarkTaskSuccess() {
        return "Understood, task marked as uncompleted.\n";
    }

    /**
     * Prints a message indicating a task has been successfully created.
     */
    public void printCreateTaskSuccess() {
        System.out.println("Confirmed. New task added:\n");
    }

    /**
     * Returns a message indicating a task has been successfully created.
     */
    public String getCreateTaskSuccess() {
        return "Confirmed. New task added:\n";
    }

    /**
     * Prints a message indicating a task has been successfully deleted.
     */
    public void printDeleteTaskSuccess() {
        System.out.println("Acknowledged. The following task has been removed:\n");
    }

    /**
     * Returns a message indicating a task has been successfully deleted.
     */
    public String getDeleteTaskSuccess() {
        return "Acknowledged. The following task has been removed:\n";
    }
}
