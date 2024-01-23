import java.util.Scanner;

public class Atlas {
    private static final int MAX_TASKS = 100;
    private final String horizontalLine = "____________________________________________________________";
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCounter = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        greet();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            else if (input.equals("list")) {
                listTasks();
            }
            else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                markTask(taskNumber);
            }
            else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7)) - 1; // Adjust for array index
                unmarkTask(taskNumber);
            }
            else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
                addTask(input);
            }


            else {
                ;
            }

        }
        exit();

    }

    private static void greet() {
        System.out.println("Hello! I'm Atlas");
        System.out.println("What can I do for you?");
    }
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addTask(String input) {
        if (input.startsWith("todo ")) {
            String description = input.substring(5);
            tasks[taskCounter] = new ToDo(description);
        } else if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by ");
            String description = parts[0];
            String dueDate = parts[1];
            tasks[taskCounter] = new Deadline(description, dueDate);
        } else if (input.startsWith("event ")) {
            String[] parts = input.substring(6).split(" /");
            String description = parts[0];
            String startTime = parts[1].substring(5); // Remove "from " prefix
            String endTime = parts[2].substring(3); // Remove "to " prefix
            tasks[taskCounter] = new Event(description, startTime, endTime);
        }
        taskCounter++;
        System.out.println("Got it. I've added this task:\n" + tasks[taskCounter - 1]);
        System.out.println("Now you have " + String.valueOf(taskCounter) + " tasks in the list.");
    }

    private static void listTasks() {
        for (int i = 0; i < taskCounter; i++) {
            System.out.println((i + 1) + "." + tasks[i].toString());
        }
    }

    private static void markTask(int i) {
        tasks[i].toggle();
        System.out.println("Nice! I've marked this task as done:");
        String str = tasks[i].toString();
        System.out.println(str);
    }

    private static void unmarkTask(int i) {
        tasks[i].toggle();
        System.out.println("OK, I've marked this task as not done yet");
        String str = tasks[i].toString();
        System.out.println(str);
    }

}
