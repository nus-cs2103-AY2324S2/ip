package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class for "bye" command.
 *
 */
public class ByeCommand extends Command {

    /**
     * Constructor for ByeCommand.
     *
     */
    public ByeCommand() {
        super(1);
    }

    @Override
    public void execute(TaskList tL, Ui ui, Storage st) {
        ui.showBye();
        setOut("Bye. Hope to see you again soon!");
    }
}
