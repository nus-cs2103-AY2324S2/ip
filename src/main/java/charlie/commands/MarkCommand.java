package charlie.commands;

import charlie.exceptions.CharlieException;
import charlie.models.Task;
import charlie.ui.Ui;
import charlie.storage.Storage;
import charlie.storage.TaskList;

// unmark command

public class MarkCommand extends Command {

    private int index;

    /**
     * constructor for MarkCommand
     * @param index index of list item to be marked or unmarked
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * executes the mark command which effectively marks tasks as completed or uncompleted
     * @param taskList - task list loaded at the start of the program.
     * @param ui       - class responsible for user interface interactions
     * @param storage  - class responsible for adding and loading tasks from and into the file
     * @throws CharlieException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CharlieException {
        Task taskToBeMarked = taskList.getTasks().get(index - 1);
        taskToBeMarked.markAsDone();
        ui.printOutput("Marked task: '" + taskToBeMarked.getDescription());
        storage.saveTasks(taskList.getTasks());
        isExit = false;
    }
}
