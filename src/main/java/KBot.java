import java.util.*;
import java.io.*;

/**
 * Encapsulate a chatbot names kaipybara that takes in input from the user and
 * perform tasks such as creating a todo list.
 *
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class KBot {
    /**
     * Creates a TaskManager to manage interactions between user and Tasks database
     */
    private static final TaskManager taskManager = new TaskManager();

    /**
     * Stores the information in String into a Task and adds into the ArrayList
     * 
     * @param info The information from userInput
     */
    private static void store(String userInput, String ins) {
        try {
            taskManager.addTask(userInput, ins);
        } catch (KException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Prints the Task List, labels them with numbers
     */
    private static void printTasks() {
        taskManager.listTasks();
    }

    /**
     * Marks a Task as completed.
     * 
     * @param index Index of Task to be marked in the TASKS ArrayList.
     */
    private static void mark(int index) {
        try {
            taskManager.markTask(index);
        } catch (KException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Marks Task as not completed.
     * 
     * @param index Index of Task to be marked in the TASKS ArrayList.
     */
    private static void unmark(int index) {
        try {
            taskManager.unmarkTask(index);
        } catch (KException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void delete(int index) {
        try {
            taskManager.delete(index);
        } catch (KException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Execute whatever command thrown at the bot by the user
     * 
     * @param userInput String representing command to the bot.
     * @throws KException Exceptions thrown when the command is not valid.
     */
    private static void executeCommand(String userInput) throws KException {
        String[] input = userInput.split(" ", 2);
        String ins = input[0];
        switch (ins) {
            case "list":
                printTasks();
                break;
            case "mark":
                int indexToMark = Integer.parseInt(input[1]);
                mark(indexToMark - 1);
                break;
            case "unmark":
                int indexToUnmark = Integer.parseInt(input[1]);
                unmark(indexToUnmark - 1);
                break;
            case "delete":
                int indexToDelete = Integer.parseInt(input[1]);
                delete(indexToDelete - 1);
                break;
            case "todo":
            case "deadline":
            case "event":
                if (input.length == 1) {
                    throw new KException("Error: " + "Invalid parameters for " + ins);
                }
                String info = input[1];
                store(info, ins);
                break;
            case "help":
                System.out.println("list\n" + "mark\n" + "unmark\n" + "todo\n" + "deadline\n" + "event\n");
            default:
                throw new KException("Invalid command: " + ins
                        + "\nPlease input the correct commands. Input help to see list of commands.");
        }
    }

    /**
     * Simulate what goes on in the chatbot.
     */
    private static void simulate() {
        Scanner sc = new Scanner(System.in); // read inputs from user
        while (true) {
            String userInput = sc.nextLine();
            System.out.println(Messages.getLine());
            // stops the program
            if (userInput.equals("bye")) {
                break;
            } else {
                try {
                    executeCommand(userInput);
                } catch (KException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            System.out.print(Messages.getLine());
        }
        ;
        sc.close();
    }

    public static void main(String[] args) {
        System.out.println(Messages.getStartMessage()); // opening statement
        simulate(); // simulate kaipybara chatbot
        System.out.println(Messages.getEndMessage() + Messages.getLine()); // closing statement
    }
}
