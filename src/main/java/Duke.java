import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String logo = " KASSIM ";
        System.out.println("YOO I AM " + logo);
        System.out.println("What can I do for you?");

        ArrayList<Task> myList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.print(" ");

        while (true) {
            String input = sc.nextLine();

            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String restOfInputs = parts.length > 1 ? parts[1] : "";

            if (command.equals("mark")) {
                System.out.println("");
                int num = Integer.parseInt(parts[1]) - 1;

                if (num >= 0 && num < myList.size()) {
                    Task task = myList.get(num);
                    task.markAsDone();

                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(task);
                    System.out.println("____________________________________________________________");

                } else {
                    System.out.println("Invalid task number.");
                }

            } else if (command.equals("unmark")) {
                System.out.println("");
                int num = Integer.parseInt(parts[1]) - 1;

                if (num >= 0 && num < myList.size()) {
                    Task task = myList.get(num);
                    task.markAsNotDone();

                    System.out.println("____________________________________________________________");
                    System.out.println("Ok, I've marked this task as not done yet: ");
                    System.out.println(task);
                    System.out.println("____________________________________________________________");

                } else {
                    System.out.println("Invalid task number.");
                }

            } else if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list: ");

                for (Task task : myList) {
                    System.out.println((myList.indexOf(task) + 1) + "." + task);
                }
                System.out.println("____________________________________________________________");

            } else if (command.equals("bye")) {
                break;

            } else {
                

                // Todo is called
                if (command.equals("todo")) {
                    String item = restOfInputs;

                    Todo newTodo = new Todo(item);
                    myList.add(newTodo);

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(newTodo);
                    System.out.println("Now you have " + myList.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }

                // Deadline is called
                else if (command.equals("deadline")) {
                    String[] item_time = restOfInputs.split("/by");
                    String item = item_time[0];
                    String time = item_time[1];

                    Deadline newDeadline = new Deadline(item, time);
                    myList.add(newDeadline);

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(newDeadline);
                    System.out.println("Now you have " + myList.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }

                // Event is called
                else {

                    String[] item_time = restOfInputs.split("/from");
                    String item = item_time[0];
                    String time = item_time[1];

                    String[] from_to = time.split("/to");
                    String from = from_to[0];
                    String to = from_to[1];

                    Event newEvent = new Event(item, from, to);
                    myList.add(newEvent);

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(newEvent);
                    System.out.println("Now you have " + myList.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }
            }

            System.out.print(" ");
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye! Hope to see you again!!");
    }
}

