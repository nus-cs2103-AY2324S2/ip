package command;

import task.TaskList;

import utilities.Storage;
import utilities.Ui;

public class ListCommand extends Command {
    /**
     * ListCommand class constructor.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Executes the listing process of all tasks in the task list.
     * @param taskList The task list that the command is applied to.
     * @param storage The storage that the task list is stored in after the command is applied.
     * @param ui Provides corresponding user output based on whether the process is successful or not.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.showList(taskList);
    }
}
