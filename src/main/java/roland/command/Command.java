package roland.command;

import roland.Storage;
import roland.TaskList;
import roland.Ui;

public class Command {




    public void execute(TaskList tasks, Ui ui, Storage storage) {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }


    public boolean isExit() {
        return false;
    }
}
