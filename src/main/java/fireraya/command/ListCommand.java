package fireraya.command;

import fireraya.main.Storage;
import fireraya.main.TaskList;
import fireraya.main.Ui;

/**
 * Class to handle a List Command in the program.
 *
 * This class is extended from the Command superclass.
 */
public class ListCommand extends Command {

    /**
     * Overrides the format to execute the command.
     *
     * @param tasks the Tasklist of program.
     * @param ui the Ui of the program.
     * @param storage the storage of the program.
     * @return String representing the message to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.listTasks(tasks.getTasks());
    }

}
