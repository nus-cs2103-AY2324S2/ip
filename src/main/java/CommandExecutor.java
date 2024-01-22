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
                    try {
                        TaskListCommands.markTaskCompleted(Integer.parseInt(commands[1]) - 1);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("  <<Duke Leto>>\n   >> I dont think you entered a number...");
                    }
                    break;
                case "unmark":
                    try {
                        TaskListCommands.markTaskUncompleted(Integer.parseInt(commands[1]) - 1);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("  <<Duke Leto>>\n   >> I dont think you entered a number...");
                    }
                    break;
                case "todo":
                case "event":
                case "deadline":
                    TaskListCommands.addToList(inputs);
                    break;
                default:
                    System.out.println("  <<Duke Leto>>\n   >> I don't know what you are asking!");

            } // End switch

        } // End command while loop
        System.out.println("  <<Duke Leto>>\n  > I bid you farewell");
    }
}
