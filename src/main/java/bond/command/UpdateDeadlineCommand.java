package bond.command;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.DeadlineTask;
import bond.task.TaskList;

/**
 * The UpdateDeadlineCommand class is used to encapsulate an update command
 * for a deadline task, which is executed upon invoking the execute() method.
 *
 * @author Benny Loh
 * @version 0.2
 */
public class UpdateDeadlineCommand extends UpdateCommand {

    private final DeadlineTask task;

    /**
     * Constructor for the UpdateDeadlineCommand class.
     *
     * @param index      The index of the task to be updated.
     * @param updateInfo The updated information of the task.
     * @param currentTask The task to be updated.
     */
    public UpdateDeadlineCommand(int index, String updateInfo, DeadlineTask currentTask) {
        super(index, updateInfo);
        this.task = currentTask;
    }
    private void updateDeadlineTask() throws BondException {
        if (containEventExclusiveInfo()) {
            BondException.raiseException("update", "INVALID_UPDATE_FIELDS");
        } else if (!super.getUpdateInfo().contains("/n") && !containDeadlineExclusiveInfo()) {
            BondException.raiseException("update", "EMPTY_DESCRIPTION");
        }

        String newTaskName;
        String newDeadline;

        newTaskName = extractFieldValue(super.getUpdateInfo(), "/n", "/n", "/d");
        newDeadline = extractFieldValue(super.getUpdateInfo(), "/d", "/d", "/n");

        if (newTaskName != null) {
            this.task.setName(newTaskName);
        }

        if (newDeadline != null) {
            task.setDeadline(newDeadline);
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        String currentTaskDescription = this.task.toString();
        updateDeadlineTask();
        String response = ui.taskUpdated(currentTaskDescription, this.task);
        storage.overwritePreviousSave(tasks);
        return response;
    }
}
