package chipchat.action;

import chipchat.exception.ChipchatException;
import chipchat.storage.Storage;
import chipchat.task.TaskList;
import chipchat.ui.Ui;

public class Mark extends Action {
    private final String SUCCESS_MSG = "Nice! I've marked this task as done:";
    private final int index;
    public Mark(int index) {
        this.index = index;
    }

    public void run(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.mark(this.index);
            storage.save(tasks);
            ui.showMsg(SUCCESS_MSG);
            ui.showMsg(tasks.printTask(this.index));
        } catch (ChipchatException x) {
            System.err.format("Exception at Mark::run(), %s\n", x);
        }
    }
}
