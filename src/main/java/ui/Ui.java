package ui;

import java.util.Scanner;

import tasks.Task;

/**
 * Ui class handles the user interface of the application.
 * It is responsible for reading user input and printing messages to the user.
 */
public class Ui {


    /*
     * Constructor for Ui class.
     * It initializes the scanner object to read user input.
     * It also prints a greeting message to the user.
     */
    public Ui() {}

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
    public String exit() {
        String bye = "█▀▀ █▀█ █▀█ █▀▄ █▄▄ █▄█ █▀▀ █\n"
                + "█▄█ █▄█ █▄█ █▄▀ █▄█ ░█░ ██▄ ▄\n";

        return "Stoppin' the YAP...\n" + bye;
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task The task that has been added.
     */
    public String triggerAddMessage(Task task) {
        return "Added task:\n" + task.getDescription();
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task The task that has been deleted.
     */
    public String triggerDeleteMessage(Task task) {
        return "Okay, I'll stop yapping about this task:\n" + task.getDescription();
    }
}
