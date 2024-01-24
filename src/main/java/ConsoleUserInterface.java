import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
        poll();
    }

    private void poll() {
        while (this.isPolling) {
            try {
                handleUserInput();
            } catch (IllegalArgumentException | NoSuchElementException e) {
                printSeparator();
                System.out.println(e.getMessage());
                printSeparator();
            }
        }
    }

    private void handleUserInput() {
        String input = this.scanner.nextLine();
        String command = input.split(" ")[0];
        String args = input.substring(input.indexOf(' ') + 1);
        boolean isSingleCommand = input.indexOf(' ') == -1;
        switch (command) {
            case "list":
                if (!isSingleCommand) {
                    throw new IllegalArgumentException("OOPS!!! Too many arguments provided.");
                }
                printSeparator();
                if (this.tasks.isEmpty()) {
                    System.out.println("Yay! You have no tasks!");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < this.tasks.size(); i++) {
                        System.out.println(i + 1 + "." + tasks.get(i));
                    }
                }
                printSeparator();
                break;
            case "bye":
                if (!isSingleCommand) {
                    throw new IllegalArgumentException("OOPS!!! Too many arguments provided.");
                }
                this.isPolling = false;
                break;
            case "mark": {
                if (this.tasks.isEmpty()) {
                    throw new NoSuchElementException("OOPS!!! There are no tasks to mark.");
                }
                if (isSingleCommand) {
                    throw new IllegalArgumentException("OOPS!!! Some arguments are missing.");
                }
                try {
                    int taskIdx = Integer.parseInt(args);
                    if (taskIdx <= 0 || taskIdx > this.tasks.size()) {
                        throw new IndexOutOfBoundsException();
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("OOPS!!! Invalid task index provided.");
                }
                Task target = this.tasks.get(Integer.parseInt(args) - 1);
                target.markDone();
                printSeparator();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + target);
                printSeparator();
                break;
            }
            case "unmark": {
                if (this.tasks.isEmpty()) {
                    throw new NoSuchElementException("OOPS!!! There are no tasks to unmark.");
                }
                if (isSingleCommand) {
                    throw new IllegalArgumentException("OOPS!!! Some arguments are missing.");
                }
                try {
                    int taskIdx = Integer.parseInt(args);
                    if (taskIdx <= 0 || taskIdx > this.tasks.size()) {
                        throw new IndexOutOfBoundsException();
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("OOPS!!! Invalid task index provided.");
                }
                Task target = this.tasks.get(Integer.parseInt(args) - 1);
                target.unmarkDone();
                printSeparator();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + target);
                printSeparator();
                break;
            }
            case "todo":
                if (isSingleCommand) {
                    throw new IllegalArgumentException("OOPS!!! The description of a todo cannot be empty.");
                }
                Task todo = new Todo(args);
                addTask(todo);
                break;
            case "deadline":
                if (isSingleCommand) {
                    throw new IllegalArgumentException("OOPS!!! The description of a deadline cannot be empty.");
                }
                String[] deadlineArgs = args.split(" /by ");
                if (deadlineArgs.length != 2) {
                    throw new IllegalArgumentException("OOPS!!! Invalid arguments provided.");
                }
                Task deadline = new Deadline(deadlineArgs[0], deadlineArgs[1]);
                addTask(deadline);
                break;
            case "event":
                if (isSingleCommand) {
                    throw new IllegalArgumentException("OOPS!!! The description of an event cannot be empty.");
                }
                String[] eventArgs = args.split(" /from ");
                if (eventArgs.length != 2) {
                    throw new IllegalArgumentException("OOPS!!! Invalid arguments provided.");
                }
                String[] startEnd = eventArgs[1].split(" /to ");
                if (startEnd.length != 2) {
                    throw new IllegalArgumentException("OOPS!!! Invalid arguments provided.");
                }
                Task event = new Event(eventArgs[0], startEnd[0], startEnd[1]);
                addTask(event);
                break;
            default:
                printSeparator();
                System.out.println("OOPS!!! Command not found.");
                printSeparator();
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
