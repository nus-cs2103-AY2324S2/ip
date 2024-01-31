package detective.command;

import detective.DukeException;
import detective.TaskList;
import detective.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        ui.showList(taskList);
    }
}
