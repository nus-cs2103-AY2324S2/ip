import java.util.Scanner;
import java.util.ArrayList;

public class Bob {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final ArrayList<Task> TASKS = new ArrayList<>();

    public static void handleMark(int taskIndex, boolean done) throws InvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= TASKS.size()) {
            throw new InvalidTaskIndexException();
        }

        Task task = TASKS.get(taskIndex);
        task.setDone(done);
        Replies.mark(task, done);
    }

    public static void handleList() {
        Replies.list(TASKS);
    }

    public static void handleAdd(String taskType, String[] parameters) {
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

        TASKS.add(task);

        Replies.add(task, TASKS.size());
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
