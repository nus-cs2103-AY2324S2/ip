import java.util.Scanner;
public class SecretaryW {
    public static void main(String[] args) {
        // Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Array to store task items
        Task[] tasklist = new Task[100];

        // counter for number of task
        int count = 0;
        String greeting = "Hello! I'm SecretaryW\n" + "What can I do for you?\n";
        String farewell = "Bye. Hope to see you again soon!\n";

        // Greetings
        System.out.println(line + greeting + line);

        // Read user input in the loop
        while (true) {
            try {
                String userInput = scanner.nextLine();

                if (userInput.equals("bye")) {
                    break; // Exits loop
                } else if (userInput.equals("list")) {
                    System.out.println(line);
                    if (count == 0) {
                        System.out.println("No tasks available");
                        System.out.println(line);
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < count; i++) {
                            Task currTask = tasklist[i];
                            System.out.println(" " + (i + 1) + ". " + tasklist[i]);
                        }
                        System.out.println(line);
                    }
                } else if (userInput.startsWith("mark")) {
                    int index = Integer.parseInt(userInput.substring(5).trim()) - 1;
                    // check for bounds
                    if (index >= 0 && index < count) {
                        tasklist[index].markAsDone();
                        System.out.println(line + " Nice! I've marked this task as done:");
                        System.out.println("  " + tasklist[index].getStatusIcon() + " " + tasklist[index].getDescription() + "\n" + line);
                    } else {
                        System.out.println(line + " Index is out of bounds!\n" + line);
                    }
                } else if (userInput.startsWith("unmark")) {
                    int index = Integer.parseInt(userInput.substring(7).trim()) - 1;
                    // check for bounds
                    if (index >= 0 && index < count) {
                        tasklist[index].markAsUndone();
                        System.out.println(line + " OK, I've marked this task as not done yet");
                        System.out.println("  " + tasklist[index].getStatusIcon() + " " + tasklist[index].getDescription() + "\n" + line);
                    } else {
                        System.out.println(line + " Index is out of bounds!\n" + line);
                    }
                } else if (userInput.startsWith("todo")) {
                    // add to do task
                    String description = userInput.substring(4).trim();
                    checkTodo(description);
                    tasklist[count] = new Todo(description);
                    count++;
                    printTaskAdded(tasklist, count);
                } else if (userInput.startsWith("deadline")) {
                    // Add a deadline task
                    String[] parts = userInput.substring(8).split("/by");
                    checkParts(parts, 2);
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    checkDeadline(by);
                    tasklist[count] = new Deadline(description, by);
                    count++;
                    printTaskAdded(tasklist, count);
                } else if (userInput.startsWith("event")) {
                    // Add an event task
                    String[] parts = userInput.substring(5).split("/from"); // first split
                    checkParts(parts, 2);
                    String description = parts[0].trim();
                    String[] time = parts[1].split("/to"); // second split
                    checkParts(time, 2);
                    String from = time[0].trim();
                    String to = time[1].trim();
                    checkEvent(from, to);
                    tasklist[count] = new Event(description, from, to);
                    count++;
                    printTaskAdded(tasklist, count);

                } else {
                    // Invalid command
                    throw new WException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (WException e) {
                System.out.println(line + "OOPS!!! " + e.getMessage() + "\n" + line);
            }
        }
        // Farewell
        System.out.println(line + farewell + line);
        scanner.close();
    }
    private static void printTaskAdded(Task[] tasklist, int count) {
        System.out.println(line + "Got it. I've added this task:\n" + " " + tasklist[count - 1]);
        System.out.println(" Now you have " + count + " tasks in the list.\n" + line);
    }

    private static String line = "-----------------------------------------------------------------\n";

    static class WException extends Exception {
        public WException(String msg) {
            super(msg);
        }
    }

    private static void checkTodo(String description) throws WException {
        if (description.isEmpty()) {
            throw new WException("The description of a todo cannot be empty");
        }
    }

    private static void checkParts(String[] parts, int length) throws WException {
        if (parts.length != length) {
            throw new WException("Wrong format. Please retype");
        }
    }

    private static void checkDeadline(String by) throws WException {
        if (by.isEmpty()) {
            throw new WException("The deadline cannot be empty");
        }
    }

    private static void checkEvent(String from, String to) throws WException {
        if (from.isEmpty() || to.isEmpty()) {
            throw new WException("The event from and to times cannot be empty");
        }
    }
}