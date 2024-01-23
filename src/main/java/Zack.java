import java.util.Scanner;

public class Zack {
    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;
    private static void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:\n  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________\n");

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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
                Task newTask;
                switch (sections[0]) {
                    case "todo":
                        newTask = new Todo(sections[1]);
                        break;
                    case "deadline": {
                        String[] parts = sections[1].split(" /by ");
                        newTask = new Deadline(parts[0], parts[1]);
                        break;
                    }
                    case "event": {
                        String[] parts = sections[1].split(" /from ");
                        String[] times = parts[1].split(" /to ");
                        newTask = new Event(parts[0], times[0], times[1]);
                        break;
                    }
                    default:
                        newTask = new Task(input);
                        break;
                }
                addTask(newTask);
            }
        }
    }

}
