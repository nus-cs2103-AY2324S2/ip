import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUserInterface {
    private Scanner scanner;
    private boolean isPolling;
    private List<Task> tasks;

    public ConsoleUserInterface() {
        this.scanner = new Scanner(System.in);
        this.isPolling = true;
        this.tasks = new ArrayList<>();
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
            String command = input.split(" ")[0];
            String args = input.substring(input.indexOf(' ') + 1);
            switch (command) {
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
                    Task target = this.tasks.get(Integer.parseInt(args) - 1);
                    target.markDone();
                    printSeparator();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + target);
                    printSeparator();
                    break;
                }
                case "unmark": {
                    Task target = this.tasks.get(Integer.parseInt(args) - 1);
                    target.unmarkDone();
                    printSeparator();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + target);
                    printSeparator();
                    break;
                }
                case "todo":
                    Task todo = new Todo(args);
                    addTask(todo);
                    break;
                case "deadline":
                    String[] deadlineArgs = args.split(" /by ");
                    Task deadline = new Deadline(deadlineArgs[0], deadlineArgs[1]);
                    addTask(deadline);
                    break;
                case "event":
                    String[] eventArgs = args.split(" /from ");
                    String[] fromTo = eventArgs[1].split(" /to ");
                    Task event = new Event(eventArgs[0], fromTo[0], fromTo[1]);
                    addTask(event);
                    break;
                default:
                    printSeparator();
                    System.out.println("invalid input");
                    printSeparator();
            }
        }
    }

    private void addTask(Task task) {
        this.tasks.add(task);
        printSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.printf("Now you have %d tasks in the list.%n", Task.getTotal());
        printSeparator();
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
