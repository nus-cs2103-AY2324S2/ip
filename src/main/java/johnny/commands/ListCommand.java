package johnny.commands;

import johnny.exceptions.JohnnyException;
import johnny.storage.Storage;
import johnny.tasks.TaskList;
import johnny.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JohnnyException {
        ui.showList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
