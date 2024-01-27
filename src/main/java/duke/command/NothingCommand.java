package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class NothingCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "";
    }
}
