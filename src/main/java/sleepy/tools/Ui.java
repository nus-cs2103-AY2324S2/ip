package sleepy.tools;

import sleepy.taskstorage.TaskList;

import java.util.Scanner;

/**
 * This class helps to print out the Sleepy AI Chatbot's lines.
 *
 * @author kjw142857
 */
public class Ui {

    public Ui() {
        String name = "Sleepy";
        String welcomeLine = "Hello! I'm " + name;
        String questionLine = "What can I do for you?";
        Ui.printLine(welcomeLine);
        Ui.printLine(questionLine);
    }

    /**
     * Prints a given line, with indentation.
     *
     * @param line Line to be printed.
     */
    public static void printLine(String line) {
        System.out.println("______________________________________________________");
        System.out.println("  " + line);
    }

    /**
     * Prints an error message.
     *
     * @param error Error which occurred.
     */
    public static void printError(String error) {
        Ui.printLine(error + " Nice try, you won't catch me sleeping :p");
    }

    /**
     * Prints the command typed in by the user.
     *
     * @param command Command entered.
     */
    public static void echoCommand(String command) {
        printLine(command);
    }

    /**
     * Prints the exit line.
     */
    public static void printExit() {
        String exitLine = "Bye. Gonna go back to sleep now *yawn*";
        printLine(exitLine);
    }

    public void acceptCommands(TaskList taskList) {
        // Await next command from user
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String nextUserCommand = userInput.nextLine().toLowerCase();
            if (nextUserCommand.equals("bye")) {
                printExit();
                break;
            }
            taskList.access(nextUserCommand);
        }
    }
}
