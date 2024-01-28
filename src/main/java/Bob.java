import java.util.Scanner;

public class Bob {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static int numberOfTasks = 0;
    private static final int MAX_NUMBER_OF_TASKS = 100;
    private static final Task[] TASKS = new Task[MAX_NUMBER_OF_TASKS];

    private static void handleMark(String taskIndex) {
        Task task = TASKS[Integer.parseInt(taskIndex) - 1];
        task.setDone(true);

        Replies.mark(task);
    }

    private static void handleUnmark(String taskIndex) {
        Task task = TASKS[Integer.parseInt(taskIndex) - 1];
        task.setDone(false);

        Replies.unmark(task);
    }

    private static Task addTodo(String todoString) {
        return new Todo(todoString);
    }

    // TODO: Generalise the following two methods
    private static Task addDeadline(String deadlineString) {
        String[] splitString = deadlineString.split(" /by ", 2);
        String by = splitString[1];

        return new Deadline(splitString[0], by);
    }

    private static Task addEvent(String eventString) {
        String[] splitString = eventString.split(" /to ", 2);
        String to = splitString[1];

        splitString = splitString[0].split(" /from ", 2);
        String from = splitString[1];

        return new Event(splitString[0], from, to);
    }

    private static void handleAdd(String[] commandArgs) {
        if (numberOfTasks == MAX_NUMBER_OF_TASKS) {
            Replies.print(Replies.EXCEEDED_MAX_NUMBER_OF_TASKS);
            return;
        }

        Task task;
        switch (commandArgs[0]) {
        case Commands.TODO:
            task = addTodo(commandArgs[1]);
            break;
        case Commands.DEADLINE:
            task = addDeadline(commandArgs[1]);
            break;
        default:
            task = addEvent(commandArgs[1]);
        }

        TASKS[numberOfTasks] = task;
        numberOfTasks++;

        Replies.print(String.format(Replies.ADD, task));
    }

    public static void main(String[] args) {
        Replies.print(Replies.GREET);

        while (true) {
            String command = SCANNER.nextLine();

            // TODO: treat invalid commands like "exit door", "list restaurants" as tasks (default)
            String[] commandArgs = command.split(" ", 2);

            if (commandArgs[0].equals(Commands.EXIT)) {
                Replies.print(Replies.EXIT);
                break;
            }

            switch (commandArgs[0]) {
            case Commands.LIST:
                Replies.list(TASKS, numberOfTasks);
                break;
            case Commands.MARK:
                handleMark(commandArgs[1]);
                break;
            case Commands.UNMARK:
                handleUnmark(commandArgs[1]);
                break;
            case Commands.TODO:
                // Fallthrough
            case Commands.DEADLINE:
                // Fallthrough
            case Commands.EVENT:
                handleAdd(commandArgs);
                break;
            default:
                Replies.print(Replies.INVALID_COMMAND);
            }
        }
    }
}
