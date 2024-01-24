import java.util.Scanner;

public class Zack {
    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;

    private static void handleInput(String input) throws ZackException {
        String[] sections = input.split(" ", 2);

        // Grab first word, which is always the keyword
        switch (sections[0].toLowerCase()) {
            case "bye":
                handleBye(sections);
                break;
            case "mark":
                handleMark(sections, true);
                break;
            case "unmark":
                handleMark(sections, false);
                break;
            case "list":
                handleList(sections);
                break;
            // let all tasks 'fallthrough'
            case "todo":
            case "deadline":
            case "event":
                handleTask(sections);
                break;
            default:
                throw new ZackException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void handleBye(String[] sections) throws ZackException {
        if (sections.length > 1) {
            throw new ZackException("Please only type 'bye' if you want to quit.");
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        System.exit(0);
    }

    private static void handleMark(String[] sections, boolean isDone) throws ZackException {
        if (sections.length < 2) {
            throw new ZackException("No task index provided. Please specify the task index to mark.");
        }

        int index;
        try {
            index = Integer.parseInt(sections[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new ZackException("Invalid task index. Please enter a valid number.");
        }
        if (index < 0 || index >= taskCount) {
            throw new ZackException("Task index is out of range. Please enter a number between 1 and " + taskCount + ".");
        }
        System.out.println("____________________________________________________________");
        if (isDone) {
            tasks[index].markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + tasks[index]);
        } else {
            tasks[index].unmark();
            System.out.println("OK, I've marked this task as not done yet:\n" + tasks[index]);
        }
        System.out.println("____________________________________________________________\n");
    }

    private static void handleList(String[] sections) throws ZackException {
        if (sections.length > 1) {
            throw new ZackException("Please only type 'list' to view the list of tasks.");
        }
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
        System.out.println("____________________________________________________________\n");
    }

    private static void handleTask(String[] sections) throws ZackException {
        /* sections[0] here can only be 1 of 3 possibilities:
        1. "todo"
        2. "deadline"
        3. "event"
        due to how handleInput is structured. Just check
        if the task description is empty for error handling.
         */

        if (sections.length < 2) {
            throw new ZackException("The description of a " + sections[0] + " cannot be empty.");
        }

        Task newTask;
        if ("todo".equals(sections[0])) {
            newTask = new Todo(sections[1]);
        } else if ("deadline".equals(sections[0])) {
            String[] parts = sections[1].split(" /by ");
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new ZackException("The deadline command is incomplete or incorrectly formatted. " +
                        "Please include '/by' followed by the deadline.");
            }
            newTask = new Deadline(parts[0], parts[1]);
        } else {
            String[] parts = sections[1].split(" /from ");
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new ZackException("The event command is incomplete. " +
                        "Please include '/from' followed by the start time.");
            }
            String[] times = parts[1].split(" /to ");
            if (times.length < 2 || times[1].trim().isEmpty()) {
                throw new ZackException("The event command is incomplete. " +
                        "Please include '/to' followed by the end time.");
            }
            newTask = new Event(parts[0], times[0], times[1]);
        }
        addTask(newTask);
    }

    private static void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:\n" + task);
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
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new ZackException("The input cannot be empty.");
                }
                handleInput(input);
            } catch (ZackException e) {
                System.out.println("____________________________________________________________");
                System.out.println("OOPS!!! " + e.getMessage());
                System.out.println("____________________________________________________________\n");
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
