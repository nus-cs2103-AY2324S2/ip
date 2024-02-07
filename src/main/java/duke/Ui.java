package duke;
import java.util.Scanner;

/**
 * Handles UI related tasks.
 */
public class Ui {

    /**
     * The list of tasks.
     */
    private TaskList taskList;

    /**
     * Creates the Ui object and runs the startup sequence.
     * It handles all UI related methods
     */
    private Ui() {
        taskList = new TaskList();
        Storage.read(taskList);
        greet();
        listen();
    }

    /**
     * Creates a UI object. It is used to start up the chat-bot.
     */
    public static void startup() {
        Ui greg = new Ui();
    }

    /**
     * The method used to greet the user upon startup.
     */
    public void greet() {
        fillerLine();
        System.out.println("    Hello! I am Greg.\n    What can I do for you?");
        fillerLine();
    }

    /**
     * Runs the shutdown sequence.
     * Saves then says bye.
     */
    public void bye() {
        Storage.save(taskList);
        fillerLine();
        System.out.println("    Goodbye! Hope to see you again soon!");
        fillerLine();
    }

    /**
     * Prints a line of dashes
     */
    public static void fillerLine() {
        System.out.println("    _______________________________________");
    }

    /**
     * Listens for any input by the user.
     * Runs on a loop until the user inputs 'bye'.
     * Sends the input to the parser if it is not 'bye'.
     */
    public void listen() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String request = sc.nextLine();
            if (request.equals("bye")) {
                bye();
                break;
            } else {
                Parser.commands(taskList, request, false, false);
            }
        }
    }

}
