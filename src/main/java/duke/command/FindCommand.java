package duke.command;

import duke.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
public class FindCommand implements Command {
    private String keyword;

    public FindCommand(String  keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui) {
        TaskList foundList = tasks.find(keyword);
        ui.findMessage(foundList.toString());
    }

    public boolean isExit() {
        return false;
    }
}
