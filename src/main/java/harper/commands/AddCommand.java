package harper.commands;

import harper.tasks.Task;
import harper.utils.Storage;
import harper.utils.TaskList;
import harper.utils.Ui;

/**
 * Represents an add command.
 */
public class AddCommand extends Command {
    private Task task;

    //CHECKSTYLE.OFF: MissingJavadocMethod
    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.task);
        storage.save(taskList);
        return ui.printSuccessfulAdd(taskList, this.task);
    }
}
