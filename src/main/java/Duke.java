import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Task[] store = new Task[100];
        int itemCounts = 0;

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm MazeDeneroBot");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        while (true) {
            String command = scan.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i=0; i<itemCounts; i++) {
                    System.out.printf("%s. %s%n", i+1, store[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (command.startsWith("mark")) {
                int idx = Integer.parseInt(command.split(" ")[1]) - 1;
                store[idx].markAsDone();

                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(store[idx]);
                System.out.println("____________________________________________________________");
            } else if (command.startsWith("unmark")) {
                int idx = Integer.parseInt(command.split(" ")[1]) - 1;
                store[idx].unmark();

                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(store[idx]);
                System.out.println("____________________________________________________________");
            } else if (command.startsWith("deadline")) {
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");

                String[] splitCommands = command.split("/by");
                String task = splitCommands[0].split("deadline")[1].trim();
                String by = splitCommands[1].trim();

                Deadline deadline = new Deadline(task, by);
                store[itemCounts] = deadline;
                itemCounts++;

                System.out.println(deadline);
                System.out.println("Now you have " + itemCounts + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (command.startsWith("event")) {
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");

                String[] splitTasks = command.split("/from");
                String task = splitTasks[0].split("event")[1].trim();

                String[] splitTime = splitTasks[1].split("/to");
                String from = splitTime[0].trim();
                String to = splitTime[1].trim();

                Event event = new Event(task, from, to);
                store[itemCounts] = event;
                itemCounts++;

                System.out.println(event);
                System.out.println("Now you have " + itemCounts + " tasks in the list.");

                System.out.println("____________________________________________________________");
            } else if (command.startsWith("todo")) {
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");

                Todo todo = new Todo(command.split("todo")[1].trim());
                store[itemCounts] = todo;
                itemCounts++;

                System.out.println(todo);
                System.out.println("Now you have " + itemCounts + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else {
                Task newTask = new Task(command);
                store[itemCounts] = newTask;
                itemCounts++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + command);
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
