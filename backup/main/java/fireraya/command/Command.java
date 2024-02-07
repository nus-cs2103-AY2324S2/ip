package fireraya.command;

import fireraya.exception.FirerayaException;
import fireraya.main.Storage;
import fireraya.main.TaskList;
import fireraya.main.Ui;

public class Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FirerayaException {
    }

    public boolean isExit() {
        return false;
    }
}
