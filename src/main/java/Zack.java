import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Zack {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;

    public enum TaskType {
        BYE, MARK, UNMARK, LIST, TODO, DEADLINE, EVENT, DELETE
    }

    private static Storage storage = new Storage("./data/zack.txt");

    private static void handleInput(String input) throws ZackException, IOException {
        String[] sections = input.split(" ", 2);

        try {
            TaskType taskType = TaskType.valueOf(sections[0].toUpperCase());
            // Grab first word, which is always the keyword
            switch (taskType) {
            case BYE:
                handleBye(sections);
                break;
            case MARK:
                handleMark(sections, true);
                break;
            case UNMARK:
                handleMark(sections, false);
                break;
            case LIST:
                handleList(sections);
                break;
            // let all tasks 'fallthrough'
            case TODO:
            case DEADLINE:
            case EVENT:
                handleTask(sections);
                break;
            case DELETE:
                handleDelete(sections);
                break;
            }
        } catch (IllegalArgumentException e) {
            throw new ZackException("I'm sorry, but I don't know what that means :-(");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            storage.save(tasks);
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
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + tasks.get(index));
        } else {
            tasks.get(index).unmark();
            System.out.println("OK, I've marked this task as not done yet:\n" + tasks.get(index));
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
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________\n");
    }

    private static void handleTask(String[] sections) throws ZackException, IOException {
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
            newTask = new Todo(sections[1], false);
        } else if ("deadline".equals(sections[0])) {
            String[] parts = sections[1].split(" /by ");
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new ZackException("The deadline command is incomplete or incorrectly formatted. " +
                        "Please include '/by' followed by the deadline.");
            }
            newTask = new Deadline(parts[0], parts[1], false);
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
            newTask = new Event(parts[0], times[0], times[1], false);
        }
        addTask(newTask);
    }

    private static void addTask(Task task) {
        tasks.add(task);
        taskCount++;
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________\n");
    }

    private static void handleDelete(String[] sections) throws ZackException {
        if (sections.length < 2) {
            throw new ZackException("No task index provided. Please specify the task index to delete.");
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
        Task removedTask = tasks.remove(index);
        taskCount--;

        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:\n" + removedTask);
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

        // load up the saved tasks when the chatbot starts up
        try {
            tasks = storage.load();
            taskCount += tasks.size();
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks that I have loaded from storage:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
            System.out.println("____________________________________________________________\n");
        } catch (ZackException e) {
            System.out.println(e + " Starting with an empty task list.\n");
        }


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
            } catch (NumberFormatException | IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
