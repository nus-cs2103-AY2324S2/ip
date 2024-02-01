package mike.command;

import mike.ListView;
import mike.ListViewType;
import mike.MikeException;
import mike.TaskList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList) throws MikeException {
        ListView listView = new ListView(ListViewType.DESCRIPTION, keyword);
        new ListCommand(listView).execute(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
