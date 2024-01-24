import java.util.*;
public class Duke {
    public static void main(String[] args) {
/*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
 */

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
                System.out.print("____________________________________________________________");
                break;
            } else if (order.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < array.size(); i++) {
                    Task addTask = array.get(i);
                    System.out.println((i + 1) + "." + addTask.toString());
                }
                System.out.print("____________________________________________________________");
            } else if (order.contains("unmark")) {
                String[] tokens = order.split(" ");
                int number = Integer.parseInt(tokens[1]);
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                Task t = array.get(number - 1);
                t.markAsUnDone();
                System.out.println(t.toString());
                System.out.print("____________________________________________________________");
            } else if (order.contains("mark")) {
                String[] tokens = order.split(" ");
                int number = Integer.parseInt(tokens[1]);
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                Task t = array.get(number - 1);
                t.markAsDone();
                System.out.println(t.toString());
                System.out.print("____________________________________________________________");
            } else if(order.contains("todo")) {
                String task = order.substring(5);
                Task t = new Todo(task);
                array.add(t);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(t.toString());
                System.out.println("Now you have " + array.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
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
                System.out.print("____________________________________________________________");
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
                System.out.print("____________________________________________________________");
            } else {
                Task t = new Task(order);
                array.add(t);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + t.description);
                System.out.print("____________________________________________________________");
            }
        }
    }
}




