import exceptions.DukeException;

import java.util.*;

public class Duke{
    public static void formalities(String context) {
        if (context.equals("greet")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Wassup dawg, I'm Snoopy");
            System.out.println(" What can I do for you?");
            System.out.println("____________________________________________________________");
        } else if (context.equals("farewell")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Bye. Don't come back. jk!");
            System.out.println("____________________________________________________________");
        }
    }

    private static void processCommand(String maybeCommand, String[] arr, ArrayList<Task> todos) throws RuntimeException {
        switch (maybeCommand) {
            case "todo":
                if (arr.length == 1) {
                    throw new DukeException(" Nuh uh! The description of a todo cannot be empty.\n");
                }
                System.out.println("____________________________________________________________");
                System.out.println("Got it. Added this task:");
                Todo todo = new Todo(arr[1]);
                todos.add(todo);
                System.out.println(todo.toString());
                System.out.println("Now you have " + todos.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
                break;
            case "deadline":
                if (arr.length == 1) {
                    throw new DukeException(" Nuh uh! The description of a deadline cannot be empty.\nMake sure to add a deadline after the description with /by too!");
                }
                System.out.println("____________________________________________________________");
                System.out.println("Got it. Added this task:");
                String arguments[] = arr[1].split(" /by ");
                String description = arguments[0];
                String by = arguments[1];
                Deadline deadline = new Deadline(description, by);
                todos.add(deadline);
                System.out.println(deadline.toString());
                System.out.println("Now you have " + todos.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
                break;
            case "event":
                if (arr.length == 1) {
                    throw new DukeException(" Nuh uh! The description of an event cannot be empty.\nMake sure to add a from and to date after the description with /from and /to too!");
                }
                System.out.println("____________________________________________________________");
                System.out.println("Got it. Added this task:");
                // extraction of parameters
                String getDesc[] = arr[1].split(" /from ");
                String desc = getDesc[0];
                String getDates[] = getDesc[1].split(" /to ");
                String from = getDates[0];
                String to = getDates[1];

                //creating of event
                Event event = new Event(desc, from, to);
                todos.add(event);

                System.out.println(event.toString());
                System.out.println("Now you have " + todos.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
                break;
        }
    }


    public static void main(String[] args) {
        formalities("greet");

        ArrayList<Task> todos = new ArrayList<Task>();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String s;
            try {
                s = scanner.nextLine(); // Use the same Scanner object
            } catch (NoSuchElementException e) {
                System.out.println("No input found. Exiting.");
                break; // Exit the loop if no input is found
            }

            String maybeCommand;
            String arr[];
            try {
                arr = s.split(" ", 2); // String in Array format. Useful: https://www.geeksforgeeks.org/split-string-java-examples/
                maybeCommand = arr[0];
            } catch (Exception e) {
                maybeCommand = null;
                continue;
            }

            if (s.toLowerCase().equals("bye")) {
                formalities("farewell");
                break;
            } else if (s.toLowerCase().equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < todos.size(); i++) {
                    Task currTask = todos.get(i);
                    System.out.println((i + 1) + ". " + currTask.toString());
                }
                System.out.println("____________________________________________________________");
            } else if (maybeCommand.equals("mark")) {
                Integer index = Integer.valueOf(arr[1]) - 1;
                System.out.print(" Nice! I've marked this task as done:\n");
                Task currTask = todos.get(index);
                currTask.markAsDone();
                System.out.println(" " + currTask.toString());
                System.out.println("____________________________________________________________");
            } else if (maybeCommand.equals("unmark")) {
                Integer index = Integer.valueOf(arr[1]) - 1;
                System.out.println("____________________________________________________________");
                System.out.print(" OK, I've marked this task as not done yet:\n");
                Task currTask = todos.get(index);
                currTask.markAsUndone();
                System.out.println(" " + currTask.toString());
                System.out.println("____________________________________________________________");
            } else if (maybeCommand.equals("todo") || maybeCommand.equals("deadline") || maybeCommand.equals("event")) {
                try {
                    processCommand(maybeCommand, arr, todos);
                } catch (RuntimeException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("Uh ah I don't understand ya");
                System.out.println("____________________________________________________________");
            }

        }

    }
}
