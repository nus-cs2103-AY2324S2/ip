package fireraya.command;

import fireraya.main.Storage;
import fireraya.main.TaskList;
import fireraya.main.Ui;

/**
 * Class to handle an Exit Command in the program.
 *
 * This class is extended from the Command superclass.
 */
public class ExitCommand extends Command{

    /**
     * Overridden method to check the exit status of the program.
     *
     * @return Boolean to check if the program should terminate. It is true by default.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Overrides the format to execute the command.
     *
     * @param tasks the Tasklist of program.
     * @param ui the Ui of the program.
     * @param storage the storage of the program.
     * @return String representing the message to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) { return ui.end();
    }
}
