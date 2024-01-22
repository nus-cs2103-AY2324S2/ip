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
                if (sc.hasNextInt()) {
                    int num = sc.nextInt();
                    store.get(num - 1).setStatus();
                } else {
                    System.out.println("Please enter a valid task number for marking.");
                    sc.next(); // Consume the non-integer input
                }
            } else if (curr.equals("delete")) {
                if (sc.hasNextInt()) {
                    int num = sc.nextInt();
                    delete(store, num);
                } else {
                    System.out.println("Please enter a valid task number for deletion.");
                    sc.next(); // Consume the non-integer input
                }
            }
            else {
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
            String inputLine = sc.nextLine().trim();
            if (!inputLine.contains("/from") || !inputLine.contains("/to")) {
                try {
                    throw new DukeException("OOPS!!! Please provide a deadline time using '/from' and '/to'.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }

            String[] parts = inputLine.split("/from", 2);
            String description = parts[0].trim();

            // Check if the description is empty
            if (description.isEmpty()) {
                try {
                    throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }
            parts = parts[1].split("/to", 2);
            String from = parts[0].trim();
            String to = parts[1].trim();
            // Check if "from" or "to" is empty
            if (from.isEmpty() || to.isEmpty()) {
                try {
                    throw new DukeException("OOPS!!! The event timing cannot be empty.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }

            toBeAdded = new Event(description, from, to);
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

    public static void delete(ArrayList<Task> store, int num) {
        Task temp;
        if (num < 1 || num > store.size()) {
            // exception if out of bounds num
            System.out.println("Invalid task number. Please enter a valid task number.");
        } else {
            // exception if elemnt doens't exist
            temp = store.get(num - 1);
            store.remove(num - 1);
            System.out.println( "Noted. I've removed this task:");
            Task.decrementTotal();
            System.out.println( temp + "\nNow you have " + Task.getNumOfTasks() + " tasks in your list");
        }


    }

}

