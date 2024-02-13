package lrbg.codriver.command;

import lrbg.codriver.data.TaskList;
import lrbg.codriver.storage.Storage;
import lrbg.codriver.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }

    public boolean testEquals(Object obj) {
        return obj instanceof ListCommand;
    }
}
