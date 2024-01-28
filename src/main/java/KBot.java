import java.util.Scanner;

/**
 * Encapsulate a chatbot names kaipybara that takes in input from the user and
 * perform tasks such as creating a todo list.
 *
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class KBot {
    /** A TaskManager to manage interactions between user and Tasks database */
    private static TaskManager taskManager = new TaskManager();

    /**
     * Stores the information in String into a Task and adds into the ArrayList
     * 
     * @param userInput The information about the Task to store.
     * @param ins       The type of Task that we want to store.
     */
    private static void addTask(String userInput, String ins) {
        try {
            taskManager.addTask(userInput, ins);
        } catch (KException e) {
            System.out.println("Error: " + e.getMessage());
        }
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

    /**
     * Deletes an entry at index given.
     * 
     * @param index Index at which the Task will be removed from the List.
     */
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
        String[] input = userInput.trim().split(" ", 2);
        String ins = input[0];
        if (input.length > 1) { // for ins with parameter
            switch (ins) { // correct ins but wrong params handled by each method call
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
                    String info = input[1];
                    addTask(info, ins);
                    break;
                default: // incorrect ins with incorrect params handled here
                    throw new KException("Invalid command: " + ins
                            + "\nPlease input the correct commands. Input help to see list of commands.");
            }
        } else { // for ins with no parameter
            switch (ins) {
                case "list":
                    taskManager.listTasks();
                    break;
                case "help":
                    System.out.println(
                            "list\n" + "mark\n" + "unmark\n" + "delete\n" + "todo\n" + "deadline\n" + "event\n");
                    break;
                case "mark":
                case "unmark":
                case "delete":
                case "todo":
                case "deadline":
                case "event": // correct ins but no param handled
                    throw new KException("Error: " + "Invalid parameters for " + ins);
                default: // incorrect ins and no param handled here
                    throw new KException("Invalid command: " + ins
                            + "\nPlease input the correct commands. Input help to see list of commands.");
            }
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
            if (userInput.equals("bye")) { // stops the program
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
        sc.close();
    }

    public static void main(String[] args) {
        System.out.println(Messages.getStartMessage()); // opening statement
        taskManager.loadLocal(); // checking if there are local files to load
        simulate(); // simulate kaipybara chatbot
        System.out.println(Messages.getEndMessage() + Messages.getLine()); // closing statement
    }
}
