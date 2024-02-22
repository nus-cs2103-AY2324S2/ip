package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * Represents a command to set the user's name.
 */
public class NameCommand extends Command {
    private String[] words;

    /**
     * Constructs a NameCommand object with the given array of command words.
     *
     * @param words The array of strings containing the command and its arguments.
     */
    public NameCommand(String[] words) {
        super();
        this.words = words;
    }

    /**
     * Executes the name command.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param storage The storage handler.
     * @return A message indicating the success of setting the name.
     * @throws DukeException If an error occurs during the execution of the command.
     */
    @Override
    public String executeForString(TaskList tasks, UI ui, Storage storage) throws DukeException {
        boolean hasEmptyDescription = words.length == 1;
        if (hasEmptyDescription) {
            throw new EmptyDescriptionException("name");
        }
        // Extract the user's name from the command
        String userName = words[1].trim();
        // Set the user's name in the UI
        ui.setName(userName);
        // Generate a message indicating the success of setting the name
        return ui.nameMessage();
    }
}

