package charlie.commands;

import charlie.exceptions.CharlieException;
import charlie.models.Task;
import charlie.storage.Storage;
import charlie.storage.TaskList;

// unmark command

public class UnmarkCommand extends Command {

    private Integer index;

    /**
     * constructor for MarkCommand
     * @param index index of list item to be marked or unmarked
     */
    public UnmarkCommand(Integer index) {
        assert index != null : "Task number to be marked has to be specified";
        this.index = index;
    }

    /**
     * executes the mark command which effectively marks tasks as completed or uncompleted
     * @param taskList - task list loaded at the start of the program.
     * @param storage  - class responsible for adding and loading tasks from and into the file
     * @throws CharlieException
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws CharlieException {
        assert taskList != null && storage != null : "TaskList and Storage must not be null";
        Task taskToBeUnmarked = taskList.getTasks().get(index - 1);
        taskToBeUnmarked.markAsNotDone();
        String response = "Unmarked task: '" + taskToBeUnmarked.getDescription();
        storage.saveTasks(taskList.getTasks());
        isExit = false;
        return response;
    }
}
