package brian.command;

import brian.storage.Storage;
import brian.task.TaskList;
import brian.ui.TextUi;
import brian.utils.BrianException;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws BrianException {
        assert ui != null;
        assert storage != null;
        storage.save(tasks);
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
