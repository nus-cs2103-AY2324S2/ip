import java.util.Scanner;

public class Zack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        // Greeting message
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Zack");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________\n");


        // Listen for commands and exits when user types "bye"
        while (true) {
            String input = scanner.nextLine();
            String[] sections = input.split(" ", 2);

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (sections.length > 1 && sections[0].equals("mark")) {
                int index = Integer.parseInt(sections[1]) - 1;
                tasks[index].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:\n  " + tasks[index]);
                System.out.println("____________________________________________________________\n");
            } else if (sections.length > 1 && sections[0].equals("unmark")) {
                int index = Integer.parseInt(sections[1]) - 1;
                tasks[index].unmark();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[index]);
                System.out.println("____________________________________________________________\n");
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:\n");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________\n");
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________\n");
            }
        }
    }

}
