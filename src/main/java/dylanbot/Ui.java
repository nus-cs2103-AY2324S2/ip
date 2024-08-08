package dylanbot;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the Ui which deals with user interactions
 */
public class Ui { // deals with interactions with the user
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a greeting message to the console
     */
    public void sendGreeting() {
        System.out.println("Hello I am dylanbot.DylanBot! \nWhat can I do for you?");
    }

    /**
     * Prints an exit message to the console
     */
    public void sendExit() {
        System.out.println("Bye! Hope to see you again soon");
    }

    /**
     * Takes in input from the user through the console and returns it as a String
     *
     * @return User input
     * @throws DylanBotException If no input is provided
     */
    public String takeInput() throws DylanBotException {
        String input = null;
        try {
            input = sc.nextLine();
            if (input.isEmpty()) {
                throw new DylanBotException("HEY no input BETTER SAY SOMETHING");
            }
        } catch (DylanBotException e) {
            System.out.println(e);
        }
        return input;
    }

    /**
     * Prints out the given ArrayList of tasks
     *
     * @param tasks The ArrayList of tasks to be printed
     */
    public String displayTasks(ArrayList<Task> tasks) {
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            String currStr = (i + 1) + ". " + curr.toString() + "\n";
            response.append(currStr);
        }
        assert response.length() > 0 : "List of tasks should not be empty";
        return response.toString();
    }

    /**
     * Prints a message to be displayed to the console
     *
     * @param msg The specified message
     */
    public static void print(String msg) {
        System.out.println(msg);
    }

    /**
     * Takes in an Exception and prints out its message
     *
     * @param e The Exception to be printed
     */
    public void displayError(DylanBotException e) {
        System.out.println(e.getMessage());
    }

    public void displayIoError() {
        System.out.println("ERROR: Issue with IO");
    }

}
