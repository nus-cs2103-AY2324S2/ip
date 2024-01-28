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
     * Basic constructor for find command to find tasks in tasklist based on matching string.
     * 
     * @param input string to be matched to task.
     */
    public FindCommand(String input) {
        super(false);
        this.input = input;
    }

    /**
     * Method to execute the find command. 
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
