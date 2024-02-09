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
    public void sayGreeting() {
        System.out.println("Hello from Martin");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays a goodbye message to the user.
     * The goodbye message includes the name "Martin".
     */
    public void sayBye() {
        System.out.println("Bye from Martin");
    }

    public void printFoundTasks(ArrayList<Task> foundTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.println((i + 1) + "." + foundTasks.get(i));
        }
    }
}
