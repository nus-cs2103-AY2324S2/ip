package bytebuddy;

import static bytebuddy.ui.Ui.printWithSolidLineBreak;

import bytebuddy.commands.Command;
import bytebuddy.exceptions.ByteBuddyException;
import bytebuddy.parser.Parser;
import bytebuddy.storage.Storage;
import bytebuddy.tasks.TaskList;
import bytebuddy.ui.Ui;

/**
 * The {@code ByteBuddy} class represents the main application class for the ByteBuddy chatbot.
 * It initializes the chatbot, runs the main loop to process user commands, and handles exceptions.
 */
public class ByteBuddy {
    // class variables
    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;

    /**
     * Creates a new chatbot called ByteBuddy that helps with tasking.
     */
    public ByteBuddy() {
        ui = new Ui();

        try {
            storage = new Storage();
            taskList = storage.load();
        } catch (ByteBuddyException e) {
            taskList = new TaskList();
            // throw new ByteBuddyException("Error loading the list from output.txt");
            System.out.println("Error loading the list from output.txt");
        }
    }

    /**
     * Main method to run ByteBuddy chatbot.
     * @param args command line arguments.
     * @throws ByteBuddyException if there is an error during the execution of the ByteBuddy chatbot.
     */
    public static void main(String[] args) throws ByteBuddyException {
        new ByteBuddy().run();
    }

    /**
     * Runs the ByteBuddy chatbot.
     */
    public void run() {
        Ui.printStartMessage();

        // repeating user commands
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (ByteBuddyException e) {
                printWithSolidLineBreak("\t " + e.getMessage());
            }
        }

        // bye
        Ui.printByeMessage();
    }

    /**
     * Returns String reply according to user instructions
     * @param s String input by user
     */
    public String getResponse(String s) {
        try {
            Command c = Parser.parse(s);
            return c.execute(taskList, ui, storage);
        } catch (ByteBuddyException e) {
            return e.getMessage();
        }
    }
}
