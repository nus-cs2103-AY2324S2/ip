package duke.commands;

import java.util.Hashtable;

import duke.exceptions.MissingInformationException;
import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * This class implements the find Command, when executed, it searches for tasks matching user input in the
 * given TaskList.
 *
 * @author delishad21
 */
public class FindCommand extends Command {

    private String description;

    /**
     * Creates a find command object to find tasks in tasklist based on matching string.
     *
     * @param params includes string to be matched to task.
     */
    public FindCommand(Hashtable<String, String> params) {
        super(false);
        this.description = params.get("description");
    }

    /**
     * Executes the find command, finds item in the TaskList provided.
     *
     * @param tasks the current list of tasks.
     * @param ui Ui object used by bot for printing information.
     * @param storage Storage object with save file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws MissingInformationException {
        if (description.equals("")) {
            throw new MissingInformationException("description");
        }

        String toFind = description.trim();
        String foundtasks = "";

        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(toFind)) {
                foundtasks += i + "." + task + "\n";
            }
        }

        return foundtasks;

    }
}
