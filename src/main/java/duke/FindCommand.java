package duke;

import java.util.ArrayList;
public class FindCommand implements Command {
    private String keyword;

    FindCommand(String  keyword) {
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
