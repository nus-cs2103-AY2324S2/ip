package virtue;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Virtue {
    // The scanner the chatbot uses to scan users' inputs.
    Scanner sc = new Scanner(System.in);

    // The task list to be used by the chatbot.
    VirtueTaskList taskList = new VirtueTaskList();

    // A horizontal line.
    private static final String horizontalLine = "____________________________________________________________";

    // Prints with an indention.
    protected static void printWithIndent(String str) {
        System.out.println("    " + str);
    }

    // Prints a horizontal line.
    protected static void printHorizontalLine() {
        printWithIndent(horizontalLine);
    }

    // Greets the user.
    private void greet() {
        printHorizontalLine();
        printWithIndent("Hello! I'm Virtue \n    What can I do for you?");
        printHorizontalLine();
    }

    // Exits with a goodbye message.
    private void bye() {
        printHorizontalLine();
        printWithIndent("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    // Takes inputs from user until bye has been input.
    private void takeInputsUntilBye() {
        Command currentCommand;
        // While user hasn't input bye, add task to task list
        while (true) {
            // If list is input, print list, else add task to list
            try {
                String input = sc.nextLine();
                currentCommand = new Command(input);
            } catch (VirtueException e) {
                printHorizontalLine();
                System.out.println("     " + e.getMessage());
                printHorizontalLine();
                continue;
            }

            if (currentCommand.isBye()) {
                break;
            } else {
                taskList.executeCommand(currentCommand);
            }
        }
    }

    // Runs the chatbot.
    private void run() {
        taskList = VirtueFileReader.initializeTaskList();
        greet();
        takeInputsUntilBye();
        bye();
    }

    public static void main(String[] args) {
        Virtue virtue = new Virtue();
        virtue.run();
    }
}
