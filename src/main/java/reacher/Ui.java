package reacher;
import reacher.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * In charge of user interactions.
 */
public class Ui {
    Scanner scanner;

    /**
     * Creates a Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints welcome message.
     */
    public void printWelcome() {
        System.out.println("Hello!\n" +
                "I'm Reacher.\n" +
                "Give me tasks.\n" +
                "Functions are find, edit, list, add and bye");
    }

    public String listToString(ArrayList<Task> tasks) {
        StringBuilder list = new StringBuilder("Tasks:\n");
        int c = 1;
        for (Task task : tasks) {
            list.append(task.toString()).append("\n");
            c++;
        }
        return String.valueOf(list);
    }
}
