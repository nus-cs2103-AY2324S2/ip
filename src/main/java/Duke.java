import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Task[] items = new Task[100];
        int itemCount = 0;

        System.out.println("Hello! I'm GuanGuanBot");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i=0; i<itemCount; i++) {
                    System.out.printf("%s. %s%n", i+1, items[i]);
                }
            } else if (command.startsWith("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                items[index].markDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(items[index]);

            } else if (command.startsWith("unmark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                items[index].unmarkDone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(items[index]);
            }

            else if (command.startsWith("todo")) {
                System.out.println("Got it. I've added this task:");

                Todo todo = new Todo(command.substring(5, command.length()));
                items[itemCount] = todo;
                itemCount++;

                System.out.println(todo);
                System.out.printf("Now you have %s tasks in the list.%n", itemCount);

            } else if (command.startsWith("deadline")) {
                System.out.println("Got it. I've added this task:");

                String[] splittedCommand = command.split(" /by ");
                String task = splittedCommand[0].substring(9, splittedCommand[0].length());
                String by = splittedCommand[1];

                Deadline deadline = new Deadline(task, by);
                items[itemCount] = deadline;
                itemCount++;

                System.out.println(deadline);
                System.out.printf("Now you have %s tasks in the list.%n", itemCount);

            } else if (command.startsWith("event")) {
                System.out.println("Got it. I've added this task:");

                String[] splittedCommand = command.split(" /from ");
                String task = splittedCommand[0].substring(6, splittedCommand[0].length());

                String[] splittedTime = splittedCommand[1].split(" /to ");
                String from = splittedTime[0];
                String to = splittedTime[1];

                Event event = new Event(task, from, to);
                items[itemCount] = event;
                itemCount++;

                System.out.println(event);
                System.out.printf("Now you have %s tasks in the list.%n", itemCount);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
