package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;

public class ListDueInCommand extends ListCommand {
    private final int days;

    public ListDueInCommand(int days) {
        this.days = days;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.showList(taskList.listDueIn(days));
    }
}
