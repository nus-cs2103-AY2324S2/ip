import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String logo = "Chucklbot";
        System.out.println("Hello I'm " + logo + "\nWhat can I do for you? \n \nBye! Hope to see you again soon.");
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
            sc.useDelimiter("/by");
            String description = sc.next();
            sc.skip("/by");
            sc.reset();
            String time = sc.next();
            toBeAdded = new Deadline(description, time);
        }
         else if (added.equals("todo")) {

            String description = sc.nextLine();
            toBeAdded = new Todo(description);
        } else if (added.equals("event")) {
            // event project meeting /from Mon 2pm /to 4pm
            sc.useDelimiter("/from");
            String description = sc.next();
            System.out.println(description);
            sc.skip("/from");
            sc.reset();
            sc.useDelimiter("/to");
            String from = sc.next();
            System.out.println(from);
            sc.reset();
            sc.skip("/to");
            String to = sc.next();
            System.out.println(to);
            toBeAdded = new Event(description, from, to);

        } else {
             // backward compatibility
            toBeAdded = new Task(added);
        }

        store.add(toBeAdded);

        System.out.println("Got it! I've added this task.\n" + toBeAdded + "\nNow you have " + Task.getNumOfTasks() + " tasks in your list");
    }

}

