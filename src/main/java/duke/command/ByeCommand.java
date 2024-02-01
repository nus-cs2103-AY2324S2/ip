package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ByeCommand extends Command{
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        storage.save(list);
        ui.showBye();
    }
}
