package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * The TaskList class handles the printing of messages to the console
 * and provides methods to print strings in consistent format.
 */
public class Ui {

    private static final String logo = " _  _   __    ____  ____ \n"
            + "( \\/ ) /__\\  (  _ \\(  _ \\\n"
            + " \\  / /(__)\\  )   / )   /\n"
            + " (__)(__)(__)(_)\\_)(_)\\_)\n";
    private static final int dividerLength = 90;
    private static final char dividerChar = 0x2500;

    /**
     * Constructs a new Ui object.
     * This constructor does not require any parameters.
     */
    public Ui() {
        //do nothing
    }

    /**
     * Method to print section dividers to the console.
     */
    public void printDivider() {
        for (int i = 0; i < dividerLength; i++) {
            System.out.print(dividerChar);
        }
        System.out.println();
    }

    /**
     * Method to print the logo and welcome message at program startup.
     */

    public String showWelcome() {
        System.out.println(logo);
        printDivider();
        System.out.println("Ahoy! I be Yarr \nWhat be yer command, me heartie?");
        printDivider();
        return "Ahoy! I be Yarr \nWhat be yer command, me heartie?";
    }

    /**
     * Method that takes a string and prints it with section dividers above and below.
     *
     * @param message a String representing the message to be printed
     */
    public String printMessage(String message) {
        printDivider();
        System.out.println(message);
        printDivider();
        return message;
    }

    /**
     * Method that prints an ArrayList of Task objects and message as a numbered sequence of Strings.
     *
     * @param message a String containing the message to be printed before the list
     * @param tasks an ArrayList of Task objects to be printed as a numbered list
     */
    public String displayList(String message, ArrayList<Task> tasks) {
        String response = "";
        printDivider();
        System.out.println(message);
        response += message + "\n";
        int count = 1;
        for (Task task : tasks) {
            System.out.println(count + "." + task.toString());
            response += count + "." + task.toString() + "\n";
            count += 1;
        }
        printDivider();
        return response;
    }
}
