import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> store = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        StoreTask taskStorage = new StoreTask();

        printWelcomeMessage();

        try {
            store = taskStorage.loadTasks();
        } catch (DukeException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        while (true) {
            String command = scan.nextLine().trim();

            try {
                if (command.equals("bye")) {
                    printGoodbyeMessage();
                    break;
                } else if (command.equals("list")) {
                    printTaskList();
                } else if (command.startsWith("mark")) {
                    markTask(command, true);
                } else if (command.startsWith("unmark")) {
                    markTask(command, false);
                } else if (command.startsWith("deadline")) {
                    addDeadline(command);
                } else if (command.startsWith("event")) {
                    addEvent(command);
                } else if (command.startsWith("todo")) {
                    addTodo(command);
                } else if (command.startsWith("delete")) {
                    deleteTask(command);
                } else {
                    throw new DukeException("Invalid Command. I'm sorry, but I don't know what that means :-(");
                }
                taskStorage.saveTasks(store);
            } catch (DukeException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
    }

    private static void printWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm MazeDeneroBot");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private static void printTaskList() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < store.size(); i++) {
            System.out.printf("%s. %s%n", i + 1, store.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    private static void markTask(String command, boolean isDone) throws DukeException {
        try {
            int idx = Integer.parseInt(command.split(" ")[1]) - 1;
            if (idx < 0 || idx >= store.size()) {
                throw new DukeException("The task index is out of range.");
            }
            Task task = store.get(idx);
            if (isDone) {
                task.markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
            } else {
                task.unmark();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println(task);
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            throw new DukeException("The index provided is not a valid number.");
        }
    }

    private static void addDeadline(String command) throws DukeException {
        if (!command.startsWith("deadline ") || !command.contains(" /by ")) {
            throw new DukeException("The format for deadline is incorrect. Use 'deadline [description] /by [date]'.");
        }
        String taskPart = command.substring("deadline ".length());
        String[] splitCommands = taskPart.split(" /by ");
        if (splitCommands.length < 2 || splitCommands[0].isEmpty() || splitCommands[1].isEmpty()) {
            throw new DukeException("The deadline description or date is missing.");
        }
        String task = splitCommands[0].trim();
        String by = splitCommands[1].trim();

        Deadline deadline = new Deadline(task, by);
        store.add(deadline);
        printAddedTask(deadline);
    }

    private static void addEvent(String command) throws DukeException {
        if (!command.startsWith("event ") || !command.contains(" /from ") || !command.contains(" /to ")) {
            throw new DukeException("The format for event is incorrect. Use 'event [description] /from [start time] /to [end time]'.");
        }
        String taskPart = command.substring("event ".length());
        String[] splitTasks = taskPart.split(" /from ");
        if (splitTasks.length < 2 || splitTasks[0].isEmpty() || splitTasks[1].isEmpty()) {
            throw new DukeException("The event description or start time is missing.");
        }

        String[] splitTime = splitTasks[1].split(" /to ");
        if (splitTime.length < 2 || splitTime[0].isEmpty() || splitTime[1].isEmpty()) {
            throw new DukeException("The event end time or details are missing.");
        }

        String task = splitTasks[0].trim();
        String from = splitTime[0].trim();
        String to = splitTime[1].trim();

        Event event = new Event(task, from, to);
        store.add(event);
        printAddedTask(event);
    }

    private static void addTodo(String command) throws DukeException {
        if (!command.startsWith("todo ")) {
            throw new DukeException("The format for todo is incorrect. Use 'todo [description]'.");
        }
        String task = command.substring("todo ".length()).trim();
        if (task.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(task);
        store.add(todo);
        printAddedTask(todo);
    }

    private static void deleteTask(String command) throws DukeException {
        try {
            int idx = Integer.parseInt(command.split(" ")[1]) - 1;
            if (idx < 0 || idx >= store.size()) {
                throw new DukeException("The task index is out of range.");
            }
            Task removedTask = store.remove(idx);

            System.out.println("____________________________________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask);
            System.out.println("Now you have " + store.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            throw new DukeException("The index provided is not a valid number.");
        }
    }

    private static void printAddedTask(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + store.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}
