package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public class HelpCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        return ui.showHelp();
    }
}
