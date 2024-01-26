import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    // The scanner the chatbot uses to scan users' inputs.
    Scanner sc = new Scanner(System.in);

    // The task list.
    ArrayList<String> taskList = new ArrayList<>();

    // A horizontal line.
    private final String horizontalLine = "____________________________________________________________";

    // Prints with an indention.
    private void printWithIndent(String str) {
        System.out.println("    " + str);
    }

    // Prints a horizontal line.
    private void printHorizontalLine() {
        printWithIndent(horizontalLine);
    }

    // Greets the user.
    private void greet() {
        printHorizontalLine();
        printWithIndent("Hello! I'm Virtue \n    What can I do for you?");
        printHorizontalLine();
    }

    // Exits with a goodbye message.
    private void bye() {
        printHorizontalLine();
        printWithIndent("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    // Adds the task to the task list.
    private void addTask(String task) {
        taskList.add(task);
        printHorizontalLine();
        printWithIndent(" added: " + task);
        printHorizontalLine();;
    }

    // Prints the task list.
    private void printTaskList() {
        int numOfTasks = taskList.size();
        printHorizontalLine();
        for (int index = 1; index <= numOfTasks; index++) {
            printWithIndent(" " + index + ". " + taskList.get(index - 1));
        }
        printHorizontalLine();
    }

    // Adds tasks to task list until bye has been input.
    private void addTaskUntilBye() {
        // Waits for command from user
        String command = sc.nextLine();

        // While user hasn't input bye, add task to task list
        while (!Objects.equals(command, "bye")) {
            // If list is input, print list, else add task to list
            if (Objects.equals(command, "list")) {
                printTaskList();
            } else {
                addTask(command);
            }

            command = sc.nextLine();
        }
    }

    // Runs the chatbot.
    private void run() {
        greet();
        addTaskUntilBye();
        bye();
    }

    public static void main(String[] args) {
        Duke virtue = new Duke();
        virtue.run();
    }
}
