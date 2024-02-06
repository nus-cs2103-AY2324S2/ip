package chipchat.action;

import chipchat.exception.ChipchatException;
import chipchat.storage.Storage;
import chipchat.task.Task;
import chipchat.task.TaskList;
import chipchat.ui.Ui;

public class Delete extends Action {
    private final String SUCCESS_MSG = "Noted. I've removed this task:";
    private final int index;
    public Delete(int index) {
        this.index = index;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task removedTask = tasks.delete(this.index);
            storage.save(tasks);
            ui.showMsg(SUCCESS_MSG);
            ui.showMsg(removedTask.toString());
        } catch (ChipchatException x) {
            System.err.format("Exception at Delete::run(), %s", x);
        }
    }
}
