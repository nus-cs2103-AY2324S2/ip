package mike.command;

import mike.ListView;
import mike.MikeException;
import mike.Storage;
import mike.TaskList;

/**
 * Lists the tasks.
 * @author ningc
 */
public class ListCommand extends Command {
    private final ListView listView;

    /**
     * Constructor.
     * @param listView The view of the list subject to a filter.
     */
    public ListCommand(ListView listView) {
        this.listView = listView;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws MikeException {
        if (taskList.isEmpty()) {
            throw new MikeException("You have no more tasks Sulley...");
        }
        return response(taskList);
    }

    private String response(TaskList taskList) {
        return "You and I are a team.\n"
                + "Here is the task list:"
                + taskList.view(listView);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "LIST " + listView;
    }
}
