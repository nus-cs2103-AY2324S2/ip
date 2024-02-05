package eggy.command;

import eggy.exception.IncompleteTaskException;
import eggy.storage.Storage;
import eggy.task.TaskList;
import eggy.task.Task;
import eggy.ui.Ui;

import java.util.List;

public class FindCommand extends Command {
    private String name;

    public FindCommand(String[] commands) throws IncompleteTaskException {
        if (commands.length < 2) {
            throw new IncompleteTaskException("find");
        }
        this.name = commands[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.findMatchingTasks(this.name);
        ui.printMatchingTasks(matchingTasks);
    }
}