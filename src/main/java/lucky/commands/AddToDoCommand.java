package lucky.commands;

import java.io.IOException;
import java.util.ArrayList;

import lucky.storage.Storage;
import lucky.tasks.Task;
import lucky.tasks.ToDo;
import lucky.ui.Ui;

/**
 * The AddToDoCommand class is a Java class that represents a command to add a new ToDo task to a
 * list of tasks.
 */
public class AddToDoCommand extends Command {

    /**
     * Adds a ToDo task to a list of tasks, validates the input format, updates the storage and
     * prints the updated task list.
     * 
     * @param tasks An ArrayList of Task objects, representing the current list of tasks.
     * @param input An array of strings representing the user's input.
     * @throws CommandException for invalid input.
     */
    @Override
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        if (!isValidCommandLength(input)) {
            throw new CommandException(
                    "Please add the task description. (format: todo <task description>)");
        }

        ToDo todoTask = new ToDo(input[1]);
        tasks.add(todoTask);

        Storage.writeToStorage(todoTask);

        super.commandResponse = Ui.printOutput("Got it. I've added this task:\n" + todoTask.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
    }

}
