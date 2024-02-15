package fireraya.command;

import fireraya.main.Storage;
import fireraya.main.TaskList;
import fireraya.main.Ui;

public class ExitCommand extends Command{

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.end();
    }
}
