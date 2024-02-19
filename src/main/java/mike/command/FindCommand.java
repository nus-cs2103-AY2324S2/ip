package mike.command;

import mike.ListView;
import mike.ListViewType;
import mike.MikeException;
import mike.TaskList;

/**
 * Lists the tasks containing a keyword.
 * @author ningc
 */
public class FindCommand extends Command {
    private final String keyword;
    private final boolean isFuzzySearchOn;

    /**
     * Constructor.
     * @param keyword Keyword to be found.
     */
    public FindCommand(String keyword, boolean isFuzzySearchOn) {
        this.keyword = keyword;
        this.isFuzzySearchOn = isFuzzySearchOn;
    }

    @Override
    public String execute(TaskList taskList) throws MikeException {
        ListView listView = new ListView(ListViewType.DESCRIPTION, keyword, isFuzzySearchOn);
        return new ListCommand(listView).execute(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "FIND " + keyword;
    }
}
