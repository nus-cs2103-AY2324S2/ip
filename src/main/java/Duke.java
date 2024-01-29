import java.util.Scanner;

public class Duke {
    private static final int MAX_NO_OF_TASKS = 100;
    private static Task[] tasks = new Task[MAX_NO_OF_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm EchoPilot.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine().trim();
            String[] parts = userInput.split(" ", 2);
            String command = parts[0];

            if (command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (command.equalsIgnoreCase("mark") || command.equalsIgnoreCase("unmark")) {
                markAsDone(command, parts[1]);
            } else {
                createNewTask(parts);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

        scanner.close();
    }

    private static void markAsDone(String command, String taskNumberStr) {
        int taskNumber = Integer.parseInt(taskNumberStr) - 1;
        if (taskNumber >= 0 && taskNumber < taskCount) {
            if (command.equalsIgnoreCase("mark")) {
                tasks[taskNumber].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:\n  " + tasks[taskNumber]);
            } else if (command.equalsIgnoreCase("unmark")) {
                tasks[taskNumber].unmarkAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[taskNumber]);
            }
            System.out.println("____________________________________________________________");
        }
    }

    private static void createNewTask(String[] parts) {
        Task newTask = null;
        switch (parts[0].toLowerCase()) {
            case "todo":
                newTask = new Todo(parts[1]);
                break;
            case "deadline":
                String[] deadlineParts = parts[1].split(" /by ", 2);
                newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
                break;
            case "event":
                String[] eventParts = parts[1].split(" /from ", 2);
                String[] timeParts = eventParts[1].split(" /to ", 2);
                newTask = new Event(eventParts[0], timeParts[0], timeParts[1]);
                break;
        }

        if (newTask != null) {
            tasks[taskCount++] = newTask;
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:\n  " + newTask);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
            System.out.println("____________________________________________________________");
        }
    }
}
