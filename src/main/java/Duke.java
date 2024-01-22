import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String logo = "Chucklbot";
        System.out.println("Hello I'm " + logo + "\nWhat can I do for you? \n ");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> store = new ArrayList<>();

        String byeMessage = "Bye! Hope to see you again soon.";
        while (true) {
            String curr = sc.next();
            if (curr.equals("Bye") || curr.equals("bye")) {
                System.out.println(byeMessage);
                break;
            } else if (curr.equals("list")) {
                displayTasks(store);
            } else if (curr.equals("mark")) {
              int num = sc.nextInt();
              store.get(num - 1).setStatus();
            } else {
                addList(store, curr, sc);
            }

        }


    }

    // if list entered
    public static void displayTasks(ArrayList<Task> store) {
        System.out.println("Here are the items in your list :) ");
        for (int i = 0; i < store.size(); i++) {
            System.out.println((i + 1) + ". " + store.get(i));
        }
    }

    public static void addList(ArrayList<Task> store, String added, Scanner sc) {
        Task toBeAdded;

        if (added.equals("deadline")) {
            // Read the entire line
            String inputLine = sc.nextLine().trim();

            // Check if there's "/by" in the input
            if (!inputLine.contains("/by")) {
                try {
                    throw new DukeException("OOPS!!! Please provide a deadline time using '/by'.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }

            // Split the input into description and time
            String[] parts = inputLine.split("/by", 2);
            String description = parts[0].trim();
            String time = parts[1].trim();

            // Check if description or time is empty
            if (description.isEmpty() || time.isEmpty()) {
                try {
                    throw new DukeException("OOPS!!! The description and deadline time cannot be empty.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }

            toBeAdded = new Deadline(description, time);
        }

        else if (added.equals("todo")) {

            String description = sc.nextLine().trim();
            if (description.isEmpty()) {
                try {
                    throw new DukeException.EmptyTodoDescriptionException();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }
            toBeAdded = new Todo(description);
        } else if (added.equals("event")) {
            // event project meeting /from Mon 2pm /to 4pm
            sc.useDelimiter("/from");
            String description = sc.next().trim();
            sc.skip("/from");
            sc.reset();
            sc.useDelimiter("/to");
            String from = sc.next().trim();
            sc.reset();
            sc.skip("/to");
            String to = sc.nextLine().trim();
            try {
                if (description.isEmpty()) {
                    throw new DukeException.EmptyTodoDescriptionException();
                }
                if (from.isEmpty() || to.isEmpty()) {
                    throw new DukeException("OOPS!!! The event time cannot be empty.");
                }
                toBeAdded = new Event(description, from, to);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                return;
            }
        } else {
            try {
                throw new DukeException.UnknownCommandException();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        store.add(toBeAdded);

        System.out.println("Got it! I've added this task.\n" + toBeAdded + "\nNow you have " + Task.getNumOfTasks() + " tasks in your list");
    }

}

