import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUserInterface {
    private Scanner scanner;
    private boolean isPolling;
    private List<Task> tasks;
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
            String[] args = input.split(" ");
            switch (args[0]) {
                case "list":
                    printSeparator();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < this.tasks.size(); i++) {
                        System.out.println(i + 1 + "." + tasks.get(i));
                    }
                    printSeparator();
                    break;
                case "bye":
                    this.isPolling = false;
                    break;
                case "mark": {
                    Task target = this.tasks.get(Integer.parseInt(args[1]) - 1);
                    target.markDone();
                    printSeparator();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + target);
                    printSeparator();
                    break;
                }
                case "unmark": {
                    Task target = this.tasks.get(Integer.parseInt(args[1]) - 1);
                    target.unmarkDone();
                    printSeparator();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + target);
                    printSeparator();
                    break;
                }
                default:
                    this.taskCount++;
                    this.tasks.add(new Task(this.taskCount, input));
                    printSeparator();
                    System.out.println("added: " + input);
                    printSeparator();
            }
        }
    }

    public void exit() {
        printSeparator();
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();

        this.scanner.close();
    }

    private void printSeparator() {
        System.out.println("____________________________________________________________");
    }
}
