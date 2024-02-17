package Thames.command;

import Thames.TaskList;
import Thames.Ui;
import Thames.Storage;

/**
 * Subclass of Command that deals with displaying content of task list.
 */
public class ListCommand extends Command {
    /**
     * Displays content of given task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert storage != null: "Task list cannot be null!";
        assert tasks != null: "Ui cannot be null!";
        assert ui != null: "Storage cannot be null!";

        return ui.showList(tasks);
    }
}
