package bob;

import java.util.function.Predicate;

public class ListCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.showList(taskList.list());
    }
}
