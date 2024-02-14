package Martin;

import java.util.ArrayList;

/**
 * The Ui class represents the user interface of the application.
 * It provides methods to interact with the user and display messages.
 */
public class Ui {
    /**
     * Constructs a new Ui object.
     */
    public Ui() {

    }

    /**
     * Displays a greeting message to the user.
     * The greeting message includes the name "Martin" and a prompt for user input.
     */
    public String sayGreeting() {
        String lineOne = "Hello from Martin" + "\n";
        String lineTwo = "What can I do for you?" + "\n";
        return lineOne + lineTwo;
    }

    /**
     * Displays a goodbye message to the user.
     * The goodbye message includes the name "Martin".
     */
    public String sayBye() {
        return "Bye from Martin";
    }

    public String printFoundTasks(ArrayList<Task> foundTasks) {
        String response = ("Here are the matching tasks in your list:" + "\n");
        for (int i = 0; i < foundTasks.size(); i++) {
            response += ((i + 1) + "." + foundTasks.get(i) + "\n");
        }
        return response;
    }

    public String sayDeleted(Task removed) {
        return "Noted. I've removed this task:\n" + removed + "\n";
    }
}
