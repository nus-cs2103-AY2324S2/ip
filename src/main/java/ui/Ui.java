package ui;

import java.util.Scanner;

import tasks.Task;

/**
 * Ui class handles the user interface of the application.
 * It is responsible for reading user input and printing messages to the user.
 */
public class Ui {

    private Scanner sc;

    /*
     * Constructor for Ui class.
     * It initializes the scanner object to read user input.
     * It also prints a greeting message to the user.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /*
     * Prints a greeting message to the user when the application starts.
     */
    public void greet() {
        String logo = "▀█▀ ▄▀█ █▀ █▄▀ █▄█ ▄▀█ █▀█ █▀█ █▀▀ █▀█\n"
                + "░█░ █▀█ ▄█ █░█ ░█░ █▀█ █▀▀ █▀▀ ██▄ █▀▄\n";

        System.out.println("*YAP* Good morning YAPPER! *YAP*\nGreetings from\n" + logo);
    }

    /*
     * Prints a message to the user when the application stops.
     */
    public void exit() {
        String bye = "█▀▀ █▀█ █▀█ █▀▄ █▄▄ █▄█ █▀▀ █\n"
                + "█▄█ █▄█ █▄█ █▄▀ █▄█ ░█░ ██▄ ▄\n";

        System.out.println("Stoppin' the YAP...\n" + bye);
        sc.close();
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The user input as a String.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task The task that has been added.
     */
    public void triggerAddMessage(Task task) {
        if (task == null) {
            return;
        }
        System.out.println("Added task:\n" + task);
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task The task that has been deleted.
     */
    public void triggerDeleteMessage(Task task) {
        if (task == null) {
            return;
        }
        System.out.println("Okay, I'll stop yapping about this task:\n" + task);
    }
}
