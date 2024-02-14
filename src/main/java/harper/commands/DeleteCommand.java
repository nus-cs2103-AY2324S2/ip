package harper.commands;

import harper.tasks.Task;
import harper.utils.Storage;
import harper.utils.TaskList;
import harper.utils.Ui;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    //CHECKSTYLE.OFF: MissingJavadocMethod
    public DeleteCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task taskDeleted = taskList.deleteTask(taskIndex);
        storage.save(taskList);
        return ui.printSuccessfulDelete(taskList, taskDeleted);
    }
}
