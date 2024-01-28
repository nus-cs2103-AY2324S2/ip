import java.util.Scanner;

public class Bob {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static int numberOfTasks = 0;
    private static final int MAX_NUMBER_OF_TASKS = 100;
    private static final Task[] TASKS = new Task[MAX_NUMBER_OF_TASKS];

    public static void handleMark(int taskIndex, boolean done) {
        try {
            if (taskIndex < 0 || taskIndex >= numberOfTasks) {
                throw new InvalidTaskIndexException(Integer.toString(taskIndex));
            }
        } catch (InvalidTaskIndexException e) {
            Replies.print(e.getMessage());
            return;
        }

        Task task = TASKS[taskIndex];
        task.setDone(done);
        Replies.mark(task, done);
    }

    public static void handleList() {
        Replies.list(TASKS, numberOfTasks);
    }

    public static void handleAdd(String taskType, String[] parameters) {
        if (numberOfTasks == MAX_NUMBER_OF_TASKS) {
            Replies.print(Replies.EXCEEDED_MAX_NUMBER_OF_TASKS);
            return;
        }

        Task task;
        switch (taskType) {
        case Commands.TODO:
            task = new Todo(parameters[0]);
            break;
        case Commands.DEADLINE:
            task = new Deadline(parameters[0], parameters[1]);
            break;
        default:
            task = new Event(parameters[0], parameters[1], parameters[2]);
        }

        TASKS[numberOfTasks] = task;
        numberOfTasks++;

        Replies.add(task, numberOfTasks);
    }

    public static void main(String[] args) {
        Replies.print(Replies.GREET);

        while (true) {
            String command = SCANNER.nextLine();
            String[] commandArgs = command.split(" ", 2);

            if (commandArgs[0].equals(Commands.EXIT)) {
                Replies.print(Replies.EXIT);
                break;
            }

            Commands.processCommands(commandArgs);
        }
    }
}
