package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;


/**
 * Represents the command to find a task by searching for a keyword.
 */
public class FindCommand extends Command {

    private String arguments;


    /**
     * Constructor for FindCommand.
     *
     * @param arguments Arguments for command.
     */
    public FindCommand(String arguments) {
        this.arguments = arguments;
    }


    /**
     * Executed the find command.
     *
     * @param taskList List of tasks.
     * @param ui User Interface of chatbot.
     * @param storage Storage that stores data.
     * @throws InvalidArgumentException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidArgumentException {
        try {
            TaskList filteredList = taskList.filter(this.arguments);
            ui.findTask(filteredList);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("TODO");
        }
    }
}
