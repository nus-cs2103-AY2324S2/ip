package Echo.Parser;

import Echo.TaskManager;

import java.util.Scanner;

public class Parser {
    public static void parse(TaskManager taskManager) {
        Scanner scanner = new Scanner(System.in);
        String userCommand;

        do {
            System.out.print("\n");
            userCommand = scanner.nextLine();
            taskManager.executeCommand(userCommand);
        } while (!userCommand.equalsIgnoreCase("bye"));
    }
}
