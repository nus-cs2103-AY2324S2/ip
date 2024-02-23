package duke.commands;

import java.util.ArrayList;

import duke.exceptions.EmptyDescriptionException;
import duke.tasks.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * Class to execute FindCommand
 */
public class FindCommand extends Command {
    private String[] words;
    /**
     * Constructs a FindCommand object with the given array of command words.
     *
     * @param words The array of strings containing the command and its arguments.
     */
    public FindCommand(String[] words) {
        super();
        this.words = words;
    }
    @Override
    public String executeForString(TaskList tasks, UI ui, Storage storage) throws EmptyDescriptionException {
        boolean hasEmptyDescription = words.length == 1;
        if (hasEmptyDescription) {
            throw new EmptyDescriptionException("find");
        }
        String descriptionToFind = words[1].trim();
        ArrayList<Task> foundTasks = tasks.findTasksWithString(descriptionToFind);
        return ui.foundTaskMessage(foundTasks);
    }
}
