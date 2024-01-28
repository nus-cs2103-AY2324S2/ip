package bytebuddy;

import bytebuddy.commands.Command;
import bytebuddy.exceptions.DukeException;
import bytebuddy.parser.Parser;
import bytebuddy.storage.Storage;
import bytebuddy.tasks.TaskList;
import bytebuddy.ui.Ui;

import static bytebuddy.ui.Ui.printWithSolidLineBreak;


public class ByteBuddy {
    // class variables
    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;

    /**
     * Main method to run ByteBuddy chatbot.
     * @param args command line arguments.
     * @throws DukeException if there is an error during the execution of the ByteBuddy chatbot.
     */
    public static void main(String[] args) throws DukeException {
        new ByteBuddy().run();
    }

    /**
     * Creates a new chatbot called ByteBuddy that helps with tasking.
     * @throws DukeException if there is an error during the creation of list from previous runs.
     */
    public ByteBuddy() throws DukeException {
        ui = new Ui();
        storage = new Storage();

        try {
            taskList = storage.load();
        } catch (DukeException e) {
            taskList = new TaskList();
            throw new DukeException("Error loading the list from output.txt");
        }
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
            } catch (DukeException e) {
                printWithSolidLineBreak(e.getMessage());
            }
        }

        // bye
        Ui.printByeMessage();
    }
}
