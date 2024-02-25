package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        ui.showHelpCommand();
    }

}
