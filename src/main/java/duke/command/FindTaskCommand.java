package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import java.util.List;
import duke.task.Task;

public class FindTaskCommand extends Command {
    private String keyword;

    /**
     * Creates a FindTaskCommand with the specified keyword.
     *
     * @param keyword The keyword to use for finding tasks.
     */
    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find task command. Searches for tasks containing the keyword
     * and displays them to the user.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The UI component of the application.
     * @param storage The storage component of the application.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> foundTasks = tasks.findTasks(keyword);
        return ui.showTaskList(foundTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
