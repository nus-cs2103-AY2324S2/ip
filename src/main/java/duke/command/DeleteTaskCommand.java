package duke.command;

import duke.task.Task;
import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the Command of deleting a task in a list.
 */
public class DeleteTaskCommand extends Command {
    private int index;

    /**
     * Initializes a Command to remove a task on the given index.
     *
     * @param type the type of the Command which is delete.
     * @param index the index of the task to be removed.
     */
    public DeleteTaskCommand(Parser.Cmd type, int index) {
        super(type);
        this.index = index;
    }

    /**
     * Removes the task on the index field in the given list.
     *
     * @param taskList the given taskList from which the task is removed.
     */
    @Override
    public void run(TaskList taskList, Ui ui) {
        if (this.index > taskList.getSize() || this.index <= 0) {
            ui.informIndexOutofBound();
            return;
        }
        Task task = taskList.deleteList(this.index);
        ui.informItemRemoved(task, taskList.getSize());
    }
}
