import java.util.Scanner;

public class Bob {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static int numberOfTasks = 0;
    private static final int MAX_NUMBER_OF_TASKS = 100;
    private static final String[] TASKS = new String[MAX_NUMBER_OF_TASKS];

    private static void handleAdd(String task) {
        if (numberOfTasks == MAX_NUMBER_OF_TASKS) {
            Replies.print(Replies.EXCEEDED_MAX_NUMBER_OF_TASKS);
        } else {
            TASKS[numberOfTasks] = task;
            numberOfTasks++;
            Replies.print(String.format(Replies.ADD, task));
        }
    }

    public static void main(String[] args) {
        Replies.print(Replies.GREET);

        while (true) {
            String command = SCANNER.nextLine();

            if (command.equals(Commands.EXIT)) {
                Replies.print(Replies.EXIT);
                break;
            }

            if (command.equals(Commands.LIST)) {
                Replies.list(TASKS, numberOfTasks);
            } else {
                handleAdd(command);
            }
        }
    }
}
