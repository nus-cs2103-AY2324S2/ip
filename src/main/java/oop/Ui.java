package oop;

import java.util.Scanner;

/**
 * Represents the user interface of the Lemona task manager application.
 * Ui handles user interactions and displays messages to the user.
 */
public class Ui {
    private TaskList taskList;
    private Scanner scanner;
    private static final String LINE = "\t______________________________________________________";

    /**
     * Constructs a Ui object and initializes the scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads user input from the console.
     *
     * @return The user input as a string.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
        System.out.println(LINE);
        System.out.println("\t Hello! I'm Lemona" +
                "\n\t Would you like some vitamins?");
        System.out.println(LINE);
    }

    /**
     * Displays a goodbye message to the user.
     * Closes the scanner after displaying the message.
     */
    public void bye() {
        System.out.println(LINE);
        System.out.println("\t Bye. Don't forget to take a LEMONA!");
        System.out.println(LINE);
        scanner.close();
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param list The task list to be displayed.
     */
    public void list(TaskList list) {
        if (list.size() == 0) {
            System.out.println(LINE);
            System.out.println("\t I think you haven't had enough vitamin E."
                    + "\n\t You do not have any tasks on the list yet!"
                    + "\n\t I suggest you take some LEMONA.");
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println("\t Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("\t " + (i + 1) + ". " + list.get(i).print());
            }
            System.out.println(LINE);
        }
    }

    /**
     * Displays an error message when there's an issue loading tasks from a file.
     */
    public void showLoadingError() {
        System.out.println(LINE);
        System.out.println("\t Sorry, I think I haven't had enough vitamin C."
                + "\n\t There was an error loading file, so I had to make a new taskList for you!"
                + "\n\t I will need to go have some LEMONA.");
        System.out.println(LINE);
    }
}
