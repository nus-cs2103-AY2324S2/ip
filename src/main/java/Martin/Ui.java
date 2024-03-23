package martin;

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
        String lineTwo = "What can I do for you today?" + "\n";
        return lineOne + lineTwo;
    }

    /**
     * Displays a goodbye message to the user.
     * The goodbye message includes the name "Martin".
     */
    public String sayBye() {
        return "Bye from Martin";
    }

    /**
     * Prints the found tasks in a formatted string.
     *
     * @param foundTasks The list of tasks to be printed.
     * @return A string containing the formatted representation of the found tasks.
     */
    public String printFoundTasks(ArrayList<Task> foundTasks) {
        String response = ("Here are the matching tasks in your list:" + "\n");
        for (int i = 0; i < foundTasks.size(); i++) {
            response += ((i + 1) + "." + foundTasks.get(i) + "\n");
        }
        return response;
    }

    /**
     * Returns a string representation of the deleted task.
     *
     * @param removed The task that was removed.
     * @return A string indicating that the task has been removed.
     */
    public String sayDeleted(Task removed) {
        return "Noted. I've removed this task:\n" + removed + "\n";
    }

    public String sayHelp() {
        return "Here are the commands you can use:\n"
                + "1. todo - add a todo task\n"
                + "2. deadline - add a deadline task\n"
                + "3. event - add an event task\n"
                + "4. list - list all tasks\n"
                + "5. mark - mark a task as done\n"
                + "6. unmark - unmark a task as done\n"
                + "7. delete - delete a task\n"
                + "8. find - find tasks with a keyword\n"
                + "9. help - display help information\n"
                + "10. bye - exit the program\n";
    }
}
