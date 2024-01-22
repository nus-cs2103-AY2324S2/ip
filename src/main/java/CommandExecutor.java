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
            if (inputs.equals("bye")) {
                bye = true;
            } else if (inputs.equals("list")) {
                System.out.println();
                TaskListCommands.printList();
                System.out.println();
            } else if (commands[0].equals("mark")) {
                TaskListCommands.markTaskCompleted(Integer.parseInt(commands[1]) - 1);
            } else if (commands[0].equals("unmark")) {
                TaskListCommands.markTaskUncompleted(Integer.parseInt(commands[1]) - 1);
            } else {
                    System.out.println();
                    TaskListCommands.addToList(inputs);
                    System.out.println();
            }

        } // End command loop
        System.out.println("  <<Duke Leto>>\n  > I bid you farewell");
    }
}
