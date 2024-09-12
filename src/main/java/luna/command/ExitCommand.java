package luna.command;

import luna.Storage;
import luna.TaskList;
import luna.Ui;

/**
 * Represents an exit command which ends the program
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super(CommandType.EXIT);
    }

    /**
     * Prompts the ui to sign off and then exits the Program
     *
     * @param tl the tasklist
     * @param ui the program ui
     * @param storage listfile storage
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ui.exitMessage();
        System.exit(0);
    }


}
