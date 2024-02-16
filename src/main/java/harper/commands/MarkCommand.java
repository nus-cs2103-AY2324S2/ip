package harper.commands;

import harper.tasks.Task;
import harper.utils.Storage;
import harper.utils.TaskList;
import harper.utils.Ui;

/**
 * Represents a mark or unmark command.
 */
public class MarkCommand extends Command {
    private int taskIndex;
    private boolean isMarked;

    //CHECKSTYLE.OFF: MissingJavadocMethod
    public MarkCommand(int taskIndex, boolean isMarked) {
        super();
        this.taskIndex = taskIndex;
        this.isMarked = isMarked;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.markTask(this.taskIndex, this.isMarked);
        storage.save(tasks);
        return ui.printSuccessfulMark(task, this.isMarked);
    }
}
