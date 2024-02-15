package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

public class ListKeywordCommand extends ListCommand {
    private final String keyword;

    public ListKeywordCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.showFind(taskList.listKeyword(keyword));
    }
}
