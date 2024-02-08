package sleepy.tools;

import java.util.Scanner;

import sleepy.taskstorage.TaskList;

/**
 * This class helps to print out the Sleepy AI Chatbot's lines.
 *
 * @author kjw142857
 */
public class Ui {

    /**
     * Constructor for the Ui class. Initialises the chatbot UI
     * and prints the welcome messages
     */
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

    /**
     * Attaches the UI to the given TaskList, and waits commands
     * from the user.
     *
     * @param taskList
     */
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
