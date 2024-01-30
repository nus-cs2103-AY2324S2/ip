package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.TextUi;
import duke.task.Todo;
import duke.task.Task;

public class TodoCommand extends Command {
    private final String DETAIL;
    public TodoCommand(String taskDetails) {
        this.DETAIL = taskDetails;
    }

    @Override
    public void execute(TaskList tasksList, TextUi ui, Storage storage) throws DukeException {
        Task todo = new Todo(DETAIL);
        tasksList.addToTaskList(todo);
        storage.saveToFile(tasksList.getList());
        ui.showAddTaskMessage(todo);
        ui.showTotalCountMessage(tasksList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
