package chipchat.action;

import chipchat.exception.ChipchatException;
import chipchat.storage.Storage;
import chipchat.task.TaskList;
import chipchat.ui.Ui;

public class ListTasks extends Action {
    private final String SUCCESS_MSG = "Here are the tasks in your list:";
    public ListTasks() {
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.showMsg(SUCCESS_MSG);
            ui.showMsg(tasks.toString());
        } catch (ChipchatException x) {
            System.err.format("Exception at List::run(), %s\n", x);
        }
    }
}
