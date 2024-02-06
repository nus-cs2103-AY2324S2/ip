package chipchat.action;

import chipchat.App;
import chipchat.storage.Storage;
import chipchat.task.TaskList;
import chipchat.ui.Ui;

public abstract class Action {
    public abstract void run(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
