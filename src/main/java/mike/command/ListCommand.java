package mike.command;

import mike.ListView;
import mike.MikeException;
import mike.TaskList;
import mike.Ui;

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
    public void execute(TaskList taskList) throws MikeException {
        if (taskList.isEmpty()) {
            throw new MikeException("You have no more tasks Sulley...");
        }
        Ui.display("You and I are a team.\nHere is the task list:");
        taskList
                .stream()
                .filter(task -> task.inListView(listView))
                .forEach(task -> {
                    int index = taskList.indexOf(task) + 1;
                    Ui.display(index + "." + task);}
                );
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
