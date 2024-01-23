import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains the logic for the ChatBot named 'Campus'
 */
public class Campus {
    static List<Task> tasks = new ArrayList<>();

    /**
     * Main Driver Logic of the Campus Class which handles user inputs and sorts them into cases
     */
    public static void main(String[] args) {
        Campus.greet();

        // Main Logic to keep the Chat Bot running until a user decides to quit talking by sending 'bye'
        String userInput;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            userInput = scanner.nextLine();
            if (Objects.equals(userInput, "bye")) {
                scanner.close();
                break;
            } else if (Objects.equals(userInput, "list")) {
                Campus.display();
            } else {
                Task task = new Task(userInput);
                Campus.add(task);
            }
        }

        Campus.exit();
    }

    /**
     * Displays all the tasks currently stored in the Campus Class by iterating through the list of type Task
     * and adding it to a final string
     */
    public static void display() {
        int numOfTasks = Campus.tasks.size();
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < numOfTasks; i++) {
            int listNum = i + 1;
            Task task = Campus.tasks.get(i);
            listOfTasks.append(String.format("%s. %s\n", listNum, task.toString()));
        }

        String message = "------------------------------\n"
                + String.format("%s\n", listOfTasks)
                + "------------------------------\n";

        System.out.println(message);
    }

    /**
     * Adds a Task instance to the list of tasks in the Campus Class
     * @param task an instance of the Task Class
     */
    public static void add(Task task) {
        Campus.tasks.add(task);

        String message = "------------------------------\n"
                + String.format("added: %s\n", task.toString())
                + "------------------------------\n";

        System.out.println(message);
    }

    public static void greet() {
        String message = "------------------------------\n"
                + "Hello! I'm Campus\n"
                + "What can I do for you?\n"
                + "\n"
                + "------------------------------\n";

        System.out.println(message);
    }

    public static void exit() {
        String message = "------------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "\n"
                + "------------------------------\n";
        System.out.println(message);
    }
}
