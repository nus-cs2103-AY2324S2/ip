package lucky.commands;

import java.io.IOException;
import java.util.ArrayList;

import lucky.tasks.Task;
import lucky.ui.Ui;

/**
 * The ExitCommand class is responsible for exiting the application.
 */
public class ExitCommand extends Command {
    /**
     * The function prints a goodbye message and exits the program.
     * 
     * @param tasks An ArrayList of Task objects. Can enter empty tasks.
     * @param input An array of strings representing the user's input. 
     *              Can enter empty input.
     */
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        super.commandResponse = Ui.printOutput("Goodbye my friend. See you soon!");
        System.exit(0);
    }
}
