package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;
import duke.tasks.Task;

public class Ui {
    public final static String indentation = " ".repeat(3);
    public final static String subIndentation = indentation + " ";
    public final static String divider = "_".repeat(60);
    private final static String logo =
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
     */
    public static void printOutput(String... msg) {
        System.out.println(indentation + divider);

        for (String string : msg) {
            System.out.println(subIndentation + string);
        }
        System.out.println(indentation + divider + "\n");
    }

    /**
     * Prints the list of tasks in the provided ArrayList of tasks.
     * 
     * @param tasks ArrayList of task to output.
     */
    public static void printList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task task : tasks) {
            sb.append(i + "." + task.toString() + "\n" + subIndentation);
            i++;
        }
        printOutput("Here are the tasks in your list:", sb.toString());
    }

    /**
     * Reads the command input of the user and returns it.
     * 
     * @return Returns an array of String, containing the command.
     */
    public String[] readCommand() {
        return this.sc.nextLine().trim().split(" ", 2);
    }

    /**
     * Prints a welcome message.
     */
    public void printWelcomeMsg() {
        Ui.printOutput(logo, "Hello! I'm Lucky the cat", "What can I do for you?");
    }

    /**
     * Closes the scanner.
     */
    public void exit() {
        this.sc.close();
    }
}
