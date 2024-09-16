package harper.commands;

import harper.tasks.Task;
import harper.utils.Storage;
import harper.utils.TaskList;
import harper.utils.Ui;

/**
 * Represents an update command.
 */
public class UpdateCommand extends Command {

    private int taskIndex;
    private String field;

    //CHECKSTYLE.OFF: MissingJavadocMethod
    public UpdateCommand(int taskIndex, String field) {
        this.taskIndex = taskIndex;
        this.field = field;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.updateTask(this.taskIndex, this.field);
        storage.save(tasks);
        return ui.printSuccessfulUpdate(task);
    }
}
