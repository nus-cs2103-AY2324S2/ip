import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUserInterface {
    private Scanner scanner;
    private boolean isPolling;
    private List<String> tasks;
    private int taskCount;

    public ConsoleUserInterface() {
        this.scanner = new Scanner(System.in);
        this.isPolling = true;
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
    }

    public void greetUser() {
        printSeparator();
        System.out.println("Hello! I'm MicroManager");
        System.out.println("What can I do for you?");
        printSeparator();
        handleUserInput();
    }

    private void handleUserInput() {
        while (isPolling) {
            String input = this.scanner.nextLine();
            switch (input) {
                case "list":
                    printOutput(tasks);
                    break;
                case "bye":
                    this.isPolling = false;
                    break;
                default:
                    this.tasks.add(taskCount + 1 + ". " + input);
                    this.taskCount++;
                    printOutput("added: " + input);
            }
        }
    }

    public void printOutput(String string) {
        printSeparator();
        System.out.println(string);
        printSeparator();
    }

    public void printOutput(List<String> collection) {
        printSeparator();
        for (String task: this.tasks) {
            System.out.println(task);
        }
        printSeparator();
    }

    public void exit() {
        printOutput("Bye. Hope to see you again soon!");

        this.scanner.close();
    }

    private void printSeparator() {
        System.out.println("____________________________________________________________");
    }
}
