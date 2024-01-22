import LetoTasks.Task;
import LetoTasks.TaskListCommands;

import java.util.Scanner;

public class CommandExecutor {
    private static Scanner sc = new Scanner(System.in);
//    private HashMap<String, Function>

    public CommandExecutor() {
    }

    public static void readCommandAndExecute() {
        boolean bye = false;

        while (!bye) {
            String inputs = sc.nextLine();
            String[] commands = inputs.split(" ");

            switch (commands[0].toLowerCase()) {
                case "bye":
                    bye = true;
                    break;
                case "list":
                    System.out.println();
                    TaskListCommands.printList();
                    System.out.println();
                    break;
                case "mark":
                    TaskListCommands.markTaskCompleted(inputs);
                    break;
                case "unmark":
                    TaskListCommands.markTaskUncompleted(inputs);
                    break;
                case "todo":
                case "event":
                case "deadline":
                    TaskListCommands.addToList(inputs);
                    break;
                default:
                    System.out.println("  <<Duke Leto>>\n   >> Supported commands: list, mark, unmark, todo, event, deadline");

            } // End switch

        } // End command while loop
        System.out.println("  <<Duke Leto>>\n  > I bid you farewell");
    }
}
