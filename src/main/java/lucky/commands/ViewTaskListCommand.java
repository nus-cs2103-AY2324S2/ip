package lucky.commands;

import java.io.IOException;
import java.util.ArrayList;

import lucky.tasks.Task;
import lucky.ui.Ui;

/**
 * The ViewTaskListCommand is responsible for showing all the tasks.
 */
public class ViewTaskListCommand extends Command {
    /**
     * Prints a list of all the tasks.
     *
     * @param tasks An ArrayList of Task objects. This represents the list of tasks that the program
     *              currently has.
     * @param input An array of strings representing user input commands or arguments. Can be empty.
     */
    @Override
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        super.commandResponse = Ui.printList(tasks);
    }
}
