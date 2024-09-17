package duke.command;

import duke.task.Task;
import duke.utility.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import java.io.IOException;

public class UpdateTaskCommand extends Command {
    /* Index of the task to be updated. */
    private int indexToBeUpdated;
    /* String containing the details to be updated. */
    private String updateDetails;

    /**
     * Contructor for the updateTaskCommand Object.
     *
     * @param updateDetails String containings the details to be updated with.
     */
    public UpdateTaskCommand(String updateDetails) {
        this.updateDetails = updateDetails;
    }

    /**
     * Runs the updateTaskCommand to update the details of an existing Task in the TaskList.
     *
     * @param taskList Existing TaskList to be updated.
     * @param ui Existing UserInterface Object.
     * @param storage Existing Storage to be updated
     * @return
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] inputArr = updateDetails.split(" ");
        indexToBeUpdated = Integer.parseInt(inputArr[1]) - 1;
        updateDetails = String.join(" ", inputArr);
        Task taskToBeUpdated;
        try {
            taskToBeUpdated = taskList.getTaskStore().get(indexToBeUpdated);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("*Honk* Pengu thinks you either need a valid task number"
                    + ", consider checking the 'list' command");
        }
        try {
            String[] strArr = this.updateDetails.split(" ");
            String change = "";
            for (int k = 3; k < strArr.length; k++) {
                if (k == strArr.length - 1) {
                    change = change + strArr[k];
                } else {
                    change = change + strArr[k] + " ";
                }
            }
            taskToBeUpdated.updateTaskDescription(strArr[2], change);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
        try {
            storage.saveStorage(taskList.getTaskStore());
        } catch (IOException e) {
            return ui.showError(e.getMessage());
        }
        return ui.showUpdatedTask(taskToBeUpdated);
    }

    public boolean isExit() {
        return false;
    }
}
