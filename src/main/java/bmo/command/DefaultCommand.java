package bmo.command;

import bmo.util.Storage;
import bmo.util.TaskList;
import bmo.ui.Ui;

public class DefaultCommand extends Command {

    private final int type;

    public DefaultCommand(int type) {
        this.type = type;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.type == 0) {
            ui.printErrInvalidCommand();
            return;
        }

        if (this.type == 1) {
            ui.printErrInvalidTask();
            return;
        }

        if (this.type == 2) {
            ui.printErrInvalidDate();
            return;
        }
    }
}
