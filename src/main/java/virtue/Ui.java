package virtue;

import java.io.IOException;
import java.util.Scanner;

/** A user interface that interacts with the user. */
public class Ui {
    /** The scanner the chatbot uses to scan users' inputs. */
    private Scanner sc;

    /** The task list the commands will be applied to. */
    private VirtueTaskList tasks;

    private Storage storage;

    /** A horizontal line. */
    private static final String HORIZONTAL_LINE = "_".repeat(60);

    /** Initiates a new user interface. */
    public Ui(VirtueTaskList taskList, Storage storage) {
        sc = new Scanner(System.in);
        this.tasks = taskList;
        this.storage = storage;
    }

    /**
     * Prints with an indention.
     *
     * @param str The string to be printed.
     */
    protected static void printWithIndent(String str) {
        System.out.println("    " + str);
    }

    /** Prints a horizontal line. */
    protected static void printHorizontalLine() {
        printWithIndent(HORIZONTAL_LINE);
    }

    /** Greets the user. */
    private static void greet() {
        printHorizontalLine();
        printWithIndent("Hello! I'm Virtue \n    What can I do for you?");
        printHorizontalLine();
    }

    /** Exits with a goodbye message. */
    private static void bye() {
        printHorizontalLine();
        printWithIndent("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    /** Takes inputs from user until bye has been input. */
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
                try {
                    tasks.executeCommand(currentCommand);
                    storage.saveToFile(tasks);
                } catch (IOException e) {
                    System.out.println("OOPS! An error occurred while taking the inputs: " + e.toString());
                }
            }
        }
    }

    /** Greets the user, takes inputs until a "bye" command, then says bye to the user. */
    public void run() {
        greet();
        takeInputsUntilBye();
        bye();
    }
}
