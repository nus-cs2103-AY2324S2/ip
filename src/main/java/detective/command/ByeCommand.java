package detective.command;

import detective.DukeException;
import detective.TaskList;
import detective.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        ui.showBye();
    }
}
