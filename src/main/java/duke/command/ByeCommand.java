package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Terminates the program.
 */
public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        ui.showBye();
    }
}
