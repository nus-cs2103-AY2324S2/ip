package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
