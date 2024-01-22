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
                default:
                    System.out.println();
                    TaskListCommands.addToList(inputs);
                    System.out.println();
            } // End switch

        } // End command while loop
        System.out.println("  <<Duke Leto>>\n  > I bid you farewell");
    }
}
