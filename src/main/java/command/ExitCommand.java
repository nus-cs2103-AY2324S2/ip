package command;

import exception.BuddyException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);
            ui.showBye();
        } catch (BuddyException e) {
            ui.showSavingError();
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
