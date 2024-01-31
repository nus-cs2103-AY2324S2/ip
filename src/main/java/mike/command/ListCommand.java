package mike.command;

import mike.ListView;
import mike.MikeException;
import mike.TaskList;
import mike.Ui;

public class ListCommand extends Command {
    private final ListView listView;

    public ListCommand(ListView listView) {
        super("" );
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
                .filter(task -> task.in(listView))
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
