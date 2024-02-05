package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

public class ExitCommand extends Command {

    public ExitCommand() {

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.sayGoodBye();
    }

    public boolean isExit() {
        return true;
    }
}
