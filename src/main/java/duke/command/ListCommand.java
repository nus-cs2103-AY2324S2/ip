package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        ui.showList(taskList);
    }
}
