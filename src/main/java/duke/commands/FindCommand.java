package duke.commands;

import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * This class implements the find Command, when executed, it searches for tasks matching user input in the
 * given TaskList.
 * 
 * @author delishad21
 */
public class FindCommand extends Command{

    private String input;

    /**
     * Creates a find command object to find tasks in tasklist based on matching string.
     * 
     * @param input string to be matched to task.
     */
    public FindCommand(String input) {
        super(false);
        this.input = input;
    }

    /**
     * Executes the find command, finds item in the TaskList provided.
     * 
     * @param tasks the current list of tasks.
     * @param ui Ui object used by bot for printing information.
     * @param storage Storage object with save file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String toFind = input.substring(5).trim();
        String foundtasks = "";

        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(toFind)) {
                foundtasks += i + "." + task + "\n";
            }
        }

        ui.botPrint(foundtasks);

    }
}
