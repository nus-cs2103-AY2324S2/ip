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
            } else {
                Task todo = new Task(command);
                items[itemCount] = todo;
                itemCount++;

                System.out.println(todo);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
