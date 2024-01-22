import LetoTasks.Task;
import LetoTasks.TaskListCommands;

import java.util.Scanner;

enum Commands {
    BYE,
    LIST,
    TODO,
    EVENT,
    DEADLINE,
    MARK,
    UNMARK,
    DELETE,
    DEFAULT
}

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

            Commands cmd;
            try {
                cmd = Commands.valueOf(commands[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                cmd = Commands.DEFAULT;
            }


            switch (cmd) {
                case BYE:
                    bye = true;
                    break;
                case LIST:
                    System.out.println();
                    TaskListCommands.printList();
                    System.out.println();
                    break;
                case MARK:
                    TaskListCommands.markTaskCompleted(inputs);
                    break;
                case UNMARK:
                    TaskListCommands.markTaskUncompleted(inputs);
                    break;
                case TODO:
                case EVENT:
                case DEADLINE:
                    TaskListCommands.addToList(inputs);
                    break;
                case DELETE:
                    TaskListCommands.deleteTask(inputs);
                    break;
                default:
                    System.out.println("  <<Duke Leto>>\n   >> Supported commands:\n" +
                            "  list     -  list all tasks\n" +
                            "  mark     -  mark task as done\n" +
                            "                usage: mark <index>\n" +
                            "  unmark   -  unmark task as done, :(\n" +
                            "                usage: unmark <index>\n" +
                            "  todo     -  create a task of type todo \n" +
                            "                usage: todo <details>\n" +
                            "  deadline -  create a task of type deadline\n" +
                            "                usage: event <details> /by <end_time>\n" +
                            "  event    -  create a task of type event    \n" +
                            "                usage: event <details> /from <start_time> /to <end_time>\n" +
                            "  bye      -  exit");

            } // End switch

        } // End command while loop
        System.out.println("  <<Duke Leto>>\n  > I bid you farewell");
    }
}
