import java.util.Scanner;

public class Bob {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static int numberOfTasks = 0;
    private static final int MAX_NUMBER_OF_TASKS = 100;
    private static final Task[] TASKS = new Task[MAX_NUMBER_OF_TASKS];

    private static void handleMark(String[] commandArgs) {
        Task task = TASKS[Integer.parseInt(commandArgs[1]) - 1];
        boolean done = commandArgs[0].equals("mark");

        task.setDone(done);
        Replies.mark(task, done);
    }

    private static String[] extractParameters(String parametersString, String[] parameters) {
        // Might be able to use HashMap but a bit too fancy
        int n = parameters.length;
        String[] result = new String[n + 1];

        String[] splitString = new String[] { parametersString };
        for (int i = n - 1; i >= 0; i--) {
            splitString = splitString[0].split(" /" + parameters[i] + ' ', 2);
            result[i + 1] = splitString[1];
        }

        result[0] = splitString[0];

        return result;
    }

    private static void handleAdd(String[] commandArgs) {
        if (numberOfTasks == MAX_NUMBER_OF_TASKS) {
            Replies.print(Replies.EXCEEDED_MAX_NUMBER_OF_TASKS);
            return;
        }

        Task task;
        String[] parameters;
        switch (commandArgs[0]) {
        case Commands.TODO:
            parameters = extractParameters(commandArgs[1], new String[] {});
            task = new Todo(parameters[0]);
            break;
        case Commands.DEADLINE:
            parameters = extractParameters(commandArgs[1], new String[] { "by" });
            task = new Deadline(parameters[0], parameters[1]);
            break;
        default:
            parameters = extractParameters(commandArgs[1], new String[] { "from", "to" });
            task = new Event(parameters[0], parameters[1], parameters[2]);
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
                // Fallthrough
            case Commands.UNMARK:
                handleMark(commandArgs);
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
