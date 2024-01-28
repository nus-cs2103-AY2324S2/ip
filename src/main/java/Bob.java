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

    private static void handleAdd(String description) {
        if (numberOfTasks == MAX_NUMBER_OF_TASKS) {
            Replies.print(Replies.EXCEEDED_MAX_NUMBER_OF_TASKS);
        } else {
            Task task = new Task(description);
            TASKS[numberOfTasks] = task;
            numberOfTasks++;

            Replies.print(String.format(Replies.ADD, task));
        }
    }

    public static void main(String[] args) {
        Replies.print(Replies.GREET);

        while (true) {
            String command = SCANNER.nextLine();

            // TODO: treat invalid commands like "exit door", "list restaurants" as tasks (default)
            String[] commandArgs = command.split(" ");

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
                default:
                    handleAdd(command);
            }
        }
    }
}
