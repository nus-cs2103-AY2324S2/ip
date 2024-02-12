package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand(String input) {
        super(input);
    }

    @Override
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) {
        storage.writeToFile(tasks);
        return ui.showByeMessage();
    }

    @Override
    public boolean isBye() {
        return true;
    }
}
