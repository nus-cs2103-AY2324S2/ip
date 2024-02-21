package julia;
import java.util.Scanner;

import julia.task.Task;



/**
 * Ui class represents the user interface for Duke.
 */
public class Ui {
    private final Scanner scanner;



    /**
     * Constructs an Ui object with a Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke. What can I do for you?");
    }





    /**
     * Displays a message indicating a deleted command.
     *
     * @param str The command that was deleted.
     */
    public String deleteMessage(String str) {
        String res = "I have deleted the below command: \n" + str;
        System.out.println(res);
        return res;
    }




    /**
     * Checks if the next input is an integer.
     *
     * @return True if the next input is an integer, false otherwise.
     */
    public boolean hasNextInt() {
        return scanner.hasNextInt();
    }

    /**
     * Displays the goodbye message.
     */
    public String showGoodbyeMessage() {

        System.out.println("Bye! Hope to see you again soon.");
        return "Bye! Hope to see you again soon.";
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to display.
     */
    public String showError(String errorMessage) {
        String res = "Error: " + errorMessage;
        System.out.println(res);
        return res;
    }

    /**
     * Displays an error message for loading tasks.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file. Starting with an empty task list.");
    }

    /**
     * Displays a message indicating a marked task.
     *
     * @param task The task that was marked.
     */
    public String markedMessage(Task task) {
        String res = "\t"
                + "Nice! I've marked this task "
                + "as done:"
                + "\n"
                + "\t "
                + task;
        System.out.println(res);
        return res;
    }

    /**
     * Displays a message to the user.
     *
     * @param str The message to display.
     */
    public String showMessage(String str) {
        System.out.println(str);
        return str;
    }
}

