package lucky.commands;

import java.io.IOException;
import java.util.ArrayList;

import lucky.storage.Storage;
import lucky.tasks.Task;
import lucky.ui.Ui;

/**
 * The DeleteCommand class is responsible for deleting tasks.
 */
public class DeleteCommand extends Command {

    /**
     * Deletes a task from an ArrayList of tasks based on the user's input, and updates the storage.
     * 
     * @param tasks An ArrayList of Task objects, representing the list of tasks.
     * @param input An array of string, representing the user's input.
     * @throws CommandException for invalid input.
     */
    @Override
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        if (!isValidCommandLength(input)) {
            throw new CommandException(
                    "Please specify which task to delete. (format: delete <task no.>)");
        }
        if (Integer.parseInt(input[1]) > tasks.size() || Integer.parseInt(input[1]) < 0) {
            throw new CommandException("The tasks you indicated does not exist");
        }

        super.commandResponse = Ui.printOutput("Noted. I've removed this task: ",
                tasks.get(Integer.parseInt(input[1]) - 1).toString(),
                "Now you have " + (tasks.size() - 1) + " tasks in the list.");

        tasks.remove(Integer.parseInt(input[1]) - 1);

        Storage.writeToStorage(tasks);
    }

}
