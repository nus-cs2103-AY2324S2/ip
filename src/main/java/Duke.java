import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Greet user
        String logo = "\n /$$      /$$  /$$$$$$  /$$$$$$$  /$$$$$$$$ /$$     /$$\n"
                + "| $$$    /$$$ /$$__  $$| $$__  $$|__  $$__/|  $$   /$$/\n"
                + "| $$$$  /$$$$| $$  \\ $$| $$  \\ $$   | $$    \\  $$ /$$/ \n"
                + "| $$ $$/$$ $$| $$  | $$| $$$$$$$/   | $$     \\  $$$$/  \n"
                + "| $$  $$$| $$| $$  | $$| $$__  $$   | $$      \\  $$/   \n"
                + "| $$\\  $ | $$| $$  | $$| $$  \\ $$   | $$       | $$    \n"
                + "| $$ \\/  | $$|  $$$$$$/| $$  | $$   | $$       | $$    \n"
                + "|__/     |__/ \\______/ |__/  |__/   |__/       |__/    \n";
        System.out.println("\nHello I'm\n" + logo);
        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<Task>();

        // Main loop for command processing
        while (true) {
            // Read and process user input
            String command = scanner.nextLine();
            String[] tokens = command.split(" ", 2);
            System.out.println("\n============================================================\n");

            // Handle different commands
            try {
                if (command.equals("bye")) {
                    // Exit program
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    // List all tasks
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i).toString());
                    }
                } else if (tokens[0].equals("todo")) {
                    // Add a new todo task
                    if (tokens.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    String title = tokens[1];
                    Todo newTodo = new Todo(title);
                    tasks.add(newTodo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTodo.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (tokens[0].equals("deadline")) {
                    // Add a new deadline task
                    if (tokens.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] deadlineTokens = tokens[1].split(" /by ");
                    if (deadlineTokens.length < 2) {
                        throw new DukeException("☹ OOPS!!! The deadline details cannot be empty.");
                    }
                    String title = deadlineTokens[0];
                    String by = deadlineTokens[1];
                    Deadline newDeadline = new Deadline(title, by);
                    tasks.add(newDeadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newDeadline.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (tokens[0].equals("event")) {
                    // Add a new event task
                    if (tokens.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String[] eventTokens = tokens[1].split(" /at ");
                    if (eventTokens.length < 2) {
                        throw new DukeException("☹ OOPS!!! The event details cannot be empty.");
                    }
                    String title = eventTokens[0];
                    String at = eventTokens[1];
                    Event newEvent = new Event(title, at);
                    tasks.add(newEvent);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newEvent.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (tokens[0].equals("done")) {
                    // Mark a task as done
                    if (tokens.length < 2) {
                        throw new DukeException("☹ OOPS!!! The task number cannot be empty.");
                    }
                    if (!tokens[1].matches("\\d+")) {
                        throw new DukeException("☹ OOPS!!! The task number must be an integer.");
                    }
                    int i = Integer.parseInt(tokens[1]) - 1;
                    if (i < 0 || i >= tasks.size()) {
                        throw new DukeException("☹ OOPS!!! The task number is invalid.");
                    }
                    tasks.get(i).markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(i).toString());
                } else if (tokens[0].equals("delete")) {
                    // Mark a task as done
                    if (tokens.length < 2) {
                        throw new DukeException("☹ OOPS!!! The task number cannot be empty.");
                    }
                    if (!tokens[1].matches("\\d+")) {
                        throw new DukeException("☹ OOPS!!! The task number must be an integer.");
                    }
                    int i = Integer.parseInt(tokens[1]) - 1;
                    if (i < 0 || i >= tasks.size()) {
                        throw new DukeException("☹ OOPS!!! The task number is invalid.");
                    }
                    tasks.remove(i);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasks.get(i).toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.err.println(e);
            }

            System.out.println("\n============================================================\n");
        }

        scanner.close();
    }
}
