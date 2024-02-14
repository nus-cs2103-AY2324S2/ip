package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;

public class Ui {
    public static final String INDENTATION = " ".repeat(3);
    public static final String SUBIDENTATION = INDENTATION + " ";
    public static final String DIVIDER = "_".repeat(60);
    private static final String LOGO =
            " _               _          \n" + "    | |   _   _  ___| | ___   _ \n"
                    + "    | |  | | | |/ __| |/ / | | |      |\\__/,|   (`\\\n"
                    + "    | |__| |_| | (__|   <| |_| |    _.|o o  |_   ) )\n"
                    + "    |_____\\__,_|\\___|_|\\_\\\\__, |  -(((---(((--------\n"
                    + "                          |___/ ";

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the output of the chatbot in formatted form.
     *
     * @param msg The message to output.
     *
     * @return Returns a String, containing the response.
     */
    public static String printOutput(String... msg) {
        System.out.println(INDENTATION + DIVIDER);
        StringBuilder sb = new StringBuilder();
        for (String string : msg) {
            sb.append(string).append("\n");
        }
        return sb.toString();
    }

    /**
     * Prints the list of tasks in the provided ArrayList of tasks.
     *
     * @param tasks ArrayList of task to output.
     *
     * @return Returns a String, containing the response.
     */
    public static String printList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        int i = 1;
        for (Task task : tasks) {
            sb.append(i).append(".").append(task.toString()).append("\n");
            i++;
        }
        return sb.toString();
    }

    /**
     * Reads the command input of the user and returns it.
     *
     * @return Returns an array of String, containing the command.
     */
    public String[] readCommand() {
        return this.sc.nextLine().trim().split(" ", 2);
    }
    public String[] readCommand(String input) {
        return input.trim().split(" ", 2);
    }

    /**
     * Prints a welcome message.
     */
    public void printWelcomeMsg() {
        Ui.printOutput(LOGO, "Hello! I'm Lucky the cat", "What can I do for you?");
    }

    /**
     * Closes the scanner.
     */
    public void exit() {
        this.sc.close();
    }
}
