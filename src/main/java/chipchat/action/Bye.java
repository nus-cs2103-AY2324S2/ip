package chipchat.action;

import chipchat.storage.Storage;
import chipchat.task.TaskList;
import chipchat.ui.Ui;

public final class Bye extends Action {
    public Bye() {
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
