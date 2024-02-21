package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;


/**
 * {@inheritDoc}
 *
 * This subclass executes when the user types in invalid command.
 */
public class WrongCommand extends Command {
    /**
     * Constructs the class WrongCommand.
     */
    public WrongCommand() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showWrongFormat() + "\n" + ui.showValidCommands();
    }

    /**
     * {@inheritDoc}
     */
    public  boolean isExit() {
        return false;
    }
}
