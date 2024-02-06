package capone.commands;

import java.util.ArrayList;

import capone.Storage;
import capone.TaskList;
import capone.ui.Ui;
import capone.exceptions.CaponeException;
import capone.exceptions.InsufficientArgumentException;
import capone.tasks.ToDo;

/**
 * Represents a command to create a new ToDo task and add it to the TaskList.
 * Extends the abstract class Command.
 *
 * @author Tay Rui-Jie
 */
public class TodoCommand extends Command {

    /** List containing input parameters for TodoCommand. */
    private final ArrayList<String> inputList;

    /**
     * Constructs a TodoCommand with the specified input list.
     *
     * @param inputList The list containing input parameters for the TodoCommand.
     */
    public TodoCommand(ArrayList<String> inputList) {
        this.inputList = inputList;
    }

    /**
     * Executes the TodoCommand, creating a new ToDo task and adding it to the TaskList.
     *
     * @param taskList The TaskList to be updated.
     * @param ui       The Ui to interact with the user.
     * @param storage  The Storage for saving data.
     * @return The String output of the bot after executing the user's command.
     * @throws CaponeException If any Capone-related exception occurs.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException {
        // If the inputList has only one string, throw an error (insufficient args).
        if (inputList.size() == 1) {
            throw new InsufficientArgumentException("Please enter a description for this capone.tasks.ToDo task!\n"
                    + "Usage: todo [description]");
        }

        // Combine the remaining words into a single string.
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < inputList.size(); i++) {
            if (i == inputList.size() - 1) {
                description.append(inputList.get(i));
                break;
            }
            description.append(inputList.get(i)).append(" ");
        }

        ToDo newTodo = new ToDo(description.toString(), false);
        taskList.addTask(newTodo);
        storage.writeTasksToJsonFile(taskList);

        return ui.sendTodo(taskList);
    }
}
