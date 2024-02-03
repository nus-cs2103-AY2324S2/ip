package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<String> matches = tasks.find(this.keyword);
        ui.showResult("Here are the matching tasks in your list:");
        for (String s: matches) {
            ui.showResult(s);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
