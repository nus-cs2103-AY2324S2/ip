package bond.command;

import bond.main.BondException;
import bond.main.Storage;
import bond.main.Ui;
import bond.task.EventTask;
import bond.task.TaskList;

/**
 * The UpdateEventCommand class is used to encapsulate an update command
 * for an event task, which is executed upon invoking the execute() method.
 *
 * @author Benny Loh
 * @version 0.2
 */
public class UpdateEventCommand extends UpdateCommand {

    private final EventTask task;

    /**
     * Constructor for the UpdateEventCommand class.
     *
     * @param index      The index of the task to be updated.
     * @param updateInfo The updated information of the task.
     * @param currentTask The task to be updated.
     */
    public UpdateEventCommand(int index, String updateInfo, EventTask currentTask) {
        super(index, updateInfo);
        this.task = currentTask;
    }

    private void updateEventTask() throws BondException {
        if (containDeadlineExclusiveInfo()) {
            BondException.raiseException("update", "INVALID_UPDATE_FIELDS");
        } else if (!super.getUpdateInfo().contains("/n") && !containEventExclusiveInfo()) {
            BondException.raiseException("update", "EMPTY_DESCRIPTION");
        }

        String newTaskName;
        String newStart;
        String newEnd;

        newTaskName = extractFieldValue(super.getUpdateInfo(), "/n", "/n", "/s", "/e");
        newStart = extractFieldValue(super.getUpdateInfo(), "/s", "/s", "/n", "/e");
        newEnd = extractFieldValue(super.getUpdateInfo(), "/e", "/e", "/n", "/s");

        if (newTaskName != null) {
            this.task.setName(newTaskName);
        }

        if (newStart != null) {
            task.setStartDatetime(newStart);
        }

        if (newEnd != null) {
            task.setEndDatetime(newEnd);
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BondException {
        String currentTaskDescription = this.task.toString();
        updateEventTask();
        String response = ui.taskUpdated(currentTaskDescription, this.task);
        storage.overwritePreviousSave(tasks);
        return response;
    }
}
