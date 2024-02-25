package brian.command;

import brian.storage.Storage;
import brian.task.TaskList;
import brian.ui.TextUi;

public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        assert ui != null;
        ui.showHelpCommand();
    }

}
