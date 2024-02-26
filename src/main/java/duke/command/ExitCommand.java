package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

public class ExitCommand extends Command{
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        StringBuilder sb = new StringBuilder();
        sb.append("Bye. Hope to see you again soon!");
        ui.setCommandOutput(sb.toString());
    }
    public boolean isExit() throws DukeException {
        return true;
    }
}
