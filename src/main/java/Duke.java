import java.util.*;
public class Duke {
    /**
     * The main method for the Duke program.
     * Reads user commands and actions until the user enters "bye"
     *
     * @param args The command-line
     */
    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Doye\n" + "What can I do for you?");
        System.out.println("____________________________________________________________");

        List<Task> array = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String order = sc.nextLine();

            if (order.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (order.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < array.size(); i++) {
                    Task addTask = array.get(i);
                    System.out.println((i + 1) + "." + addTask.toString());
                }
                System.out.println("____________________________________________________________");
            } else if (order.contains("unmark")) {
                String[] tokens = order.split(" ");
                int number = Integer.parseInt(tokens[1]);
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                Task t = array.get(number - 1);
                t.markAsUnDone();
                System.out.println(t.toString());
                System.out.println("____________________________________________________________");
            } else if (order.contains("mark")) {
                String[] tokens = order.split(" ");
                int number = Integer.parseInt(tokens[1]);
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                Task t = array.get(number - 1);
                t.markAsDone();
                System.out.println(t.toString());
                System.out.println("____________________________________________________________");
            } else if(order.contains("todo")) {
                try {
                    String task = order.substring(4).trim();
                    if (task.isEmpty()) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task t = new Todo(task);
                    array.add(t);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t.toString());
                    System.out.println("Now you have " + array.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }  catch (DukeException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                }
            } else if (order.contains("deadline")) {
                int byIndex = order.indexOf("/by");
                String task = order.substring(9, byIndex - 1);
                String due = order.substring(byIndex + 4);
                Task t = new Deadline(task, due);
                array.add(t);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());
                System.out.println("Now you have " + array.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (order.contains("event")) {
                int fromIndex = order.indexOf("/from");
                int toIndex = order.indexOf("/to");
                String task = order.substring(6, fromIndex - 1);
                String from = order.substring(fromIndex + 6, toIndex);
                String to = order.substring(toIndex + 4);
                Task t = new Event(task, from, to);
                array.add(t);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());
                System.out.println("Now you have " + array.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (order.contains("delete")) {
                String[] tokens = order.split(" ");
                int number = Integer.parseInt(tokens[1]);
                System.out.println("____________________________________________________________");
                System.out.println("Noted. I've removed this task:");
                Task t = array.get(number - 1);
                System.out.println(t.toString());
                array.remove(number - 1);
                System.out.println("Now you have " + array.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("____________________________________________________________");
            }
        }
    }
}




