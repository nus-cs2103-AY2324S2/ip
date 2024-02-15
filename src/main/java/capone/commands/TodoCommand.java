package capone.commands;

import java.util.ArrayList;

import capone.Parser;
import capone.Storage;
import capone.TaskList;
import capone.exceptions.CaponeException;
import capone.exceptions.InsufficientArgumentException;
import capone.tasks.ToDo;
import capone.ui.Ui;

/**
 * Represents a command to create a new ToDo task and add it to the TaskList.
 * Extends the abstract class Command.
 *
 * @author Tay Rui-Jie
 */
public class TodoCommand extends Command {
    /**
     * Constant string that provides information to the user on how to use the command.
     */
    private static final String USAGE_STRING = "Usage: todo [description]";

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
                    + TodoCommand.USAGE_STRING);
        }

        // The starting index of the words that contain the description.
        final int descNdx = 1;
        String description = Parser.parseDescription(descNdx, inputList.size(), inputList);

        ToDo newTodo = new ToDo(description, false);
        taskList.addTask(newTodo);
        storage.writeTasksToJsonFile(taskList);

        return ui.sendTodo(taskList);
    }
}
