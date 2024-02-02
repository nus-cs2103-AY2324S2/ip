package Command;

import Utility.Storage;
import Utility.TaskList;
import Utility.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showFind(this.keyword);
        ui.listTasks(list.searchList(this.keyword));
    }

    public boolean isExit() {
        return false;
    }
}
