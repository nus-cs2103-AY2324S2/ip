package bond.command;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.TaskList;
import bond.task.TodoTask;

/**
 * The UpdateTodoCommand class is used to encapsulate an update command for a todo task,
 * which is executed upon invoking the execute() method.
 *
 * @author Benny Loh
 * @version 0.2
 */
public class UpdateTodoCommand extends UpdateCommand {

    private final TodoTask task;

    /**
     * Constructor for the UpdateTodoCommand class.
     *
     * @param index      The index of the task to be updated.
     * @param updateInfo The updated information of the task.
     * @param currentTask The task to be updated.
     */
    public UpdateTodoCommand(int index, String updateInfo, TodoTask currentTask) {
        super(index, updateInfo);
        this.task = currentTask;
    }

    private void updateTodoTask() throws BondException {
        if (super.containDeadlineExclusiveInfo() || super.containEventExclusiveInfo()) {
            BondException.raiseException("update", "INVALID_UPDATE_FIELDS");
        } else if (!super.getUpdateInfo().contains("/n")) {
            BondException.raiseException("update", "EMPTY_DESCRIPTION");
        }

        String newTaskName = super.extractFieldValue(super.getUpdateInfo(), "/n", "/n");

        this.task.setName(newTaskName.trim());
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        String currentTaskDescription = this.task.toString();
        updateTodoTask();
        String response = ui.taskUpdated(currentTaskDescription, this.task);
        storage.overwritePreviousSave(tasks);
        return response;
    }

}
