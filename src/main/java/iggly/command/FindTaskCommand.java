package iggly.command;

import iggly.duke.Storage;
import iggly.model.TaskList;
import iggly.view.FindTaskView;

/**
 * Command to list out filtered tasks in the {@link TaskList} based on user's input.
 */
public class FindTaskCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private final String title;
    private final TaskList taskList;

    /**
     * Constructs a {@link FindTaskCommand} object using user's input and {@link TaskList}.
     * This command is used to list filtered tasks in the given task list based on user's input.
     *
     * @param title The user's desired find task keyword.
     * @param taskList The task list which requires filtering.
     */
    public FindTaskCommand(String title, TaskList taskList) {
        this.title = title;
        this.taskList = taskList;
    }

    /**
     * Executes the {@link FindTaskCommand}, filter a {@link TaskList} based on user's input and
     * prints a filtered {@link TaskList}.
     *
     * @param storage The storage object that manages the data persistence. It is not used in {@link FindTaskCommand}
     */
    @Override
    public String execute(Storage storage) {
        TaskList filteredTaskList = taskList.find(title);
        FindTaskView findTaskView = new FindTaskView(filteredTaskList);
        return findTaskView.display();
    }
}
