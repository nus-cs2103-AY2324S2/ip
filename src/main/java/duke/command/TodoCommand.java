package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.TextUi;
import duke.task.Todo;

/**
 * Creates a Todo task and add it to the task list
 */

public class TodoCommand extends Command {
    private final String DETAIL;

    /**
     * Constructs a TodoCommand class with given parameter
     * @param taskDetails A string representation of user input
     */
    public TodoCommand(String taskDetails) {
        this.DETAIL = taskDetails;
    }

    @Override
    public String execute(TaskList tasksList, TextUi ui, Storage storage) throws DukeException {
        Task todo = new Todo(DETAIL);
        tasksList.addToTaskList(todo);
        storage.saveToFile(tasksList.getList());
        return ui.showAddTaskMessage(todo) + '\n'
                + ui.showTotalCountMessage(tasksList);
    }

    /**
     * Returns a boolean value to indicate whether to exit the program
     * @return a boolean value
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
