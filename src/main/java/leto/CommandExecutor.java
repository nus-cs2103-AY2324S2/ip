package leto;

import leto.tasklist.TaskList;

import java.util.Scanner;

import static leto.ui.Ui.letoHelp;

enum Commands {
    BYE,
    LIST,
    TODO,
    EVENT,
    DEADLINE,
    MARK,
    UNMARK,
    DELETE,
    SAVE,
    DEFAULT
}

public class CommandExecutor {
    private static Scanner sc = new Scanner(System.in);
//    private HashMap<String, Function>

    public CommandExecutor() {
    }

    public static void readCommandAndExecute() {
        TaskList.initFromFile();

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
                    TaskList.saveTasks();
                    break;
                case LIST:
                    System.out.println();
                    TaskList.printList();
                    System.out.println();
                    break;
                case MARK:
                    TaskList.markTaskCompleted(inputs);
                    break;
                case UNMARK:
                    TaskList.markTaskUncompleted(inputs);
                    break;
                case TODO:
                case EVENT:
                case DEADLINE:
                    TaskList.addToList(inputs);
                    break;
                case DELETE:
                    TaskList.deleteTask(inputs);
                    break;
                case SAVE:
                    TaskList.saveTasks();
                    break;
                default:
                    letoHelp();

            } // End switch

        } // End command while loop
        System.out.println("  <<Duke Leto>>\n  > I bid you farewell");
    }
}
