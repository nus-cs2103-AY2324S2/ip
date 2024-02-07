package adam.command;

import adam.AdamException;
import adam.Storage;
import adam.task.TaskList;
import adam.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AdamException {
        ArrayList<String> matches = taskList.find(keyword);
        matches.add(0, "Here are the matching tasks in your list:");
        return ui.showResult(matches.toArray(new String[0]));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
