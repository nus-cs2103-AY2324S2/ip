package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command{

    private final String keyword;

    public FindCommand(String task) {
        this.keyword = task.substring(5);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.list(tasks, this.keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
