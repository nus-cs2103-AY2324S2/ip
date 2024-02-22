/*
 * Package: Echo.Parser
 * Module/Project Name: Echo
 * File: Parser.java
 *
 * Description:
 * This class is responsible for parsing user commands and interacting with the TaskManager.
 *
 */

package echo.Parser;
import echo.TaskManager;
import java.util.Scanner;

public class Parser {
    /**
     * Parses user commands and executes them using the provided TaskManager.
     *
     * @param taskManager The TaskManager instance to execute commands on.
     */
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
