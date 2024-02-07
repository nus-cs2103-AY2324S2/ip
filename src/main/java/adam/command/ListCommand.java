package adam.command;

import adam.AdamException;
import adam.Storage;
import adam.task.Task;
import adam.task.TaskList;
import adam.ui.Ui;

import java.util.ArrayList;

/**
 * @inheritDoc
 * Represents a command to print the list of tasks.
 */
public class ListCommand extends Command {
    /**
     * Returns a command to print the list of tasks.
     */
    public ListCommand() {
    }

    /**
     * Prints the list of tasks.
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AdamException {
        ArrayList<String> taskStrings = new ArrayList<>();
        taskStrings.add("Here are your tasks:");
        for (Task t : tasks.getTasks()) {
            taskStrings.add(t.toString());
        }
        return ui.showResult(taskStrings.toArray(new String[0]));
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
