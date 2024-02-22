package haro.command;

import haro.Storage;
import haro.TaskList;
import haro.Ui;
import haro.exception.InvalidArgsException;
import haro.task.Task;

/**
 * The EditCommand class represents a command to edit tasks in the task list with the desired portions to update.
 * It extends the Command class and provides functionality to execute the edit command.
 */
public class EditCommand extends Command {
    private int taskIndex;
    private String portionToEdit;
    private String updatedPortion;

    /**
     * Constructs an EditCommand instance with the specified task index, portion to edit and updated
     * value for that portion.
     *
     * @param taskIndex Index number of the task in the task list
     * @param portionToEdit Portion of the task the user wishes to edit
     * @param updatedPortion Updated value for the portion the user wishes to edit
     */
    public EditCommand(int taskIndex, String portionToEdit, String updatedPortion) {
        super(false);
        this.taskIndex = taskIndex;
        this.portionToEdit = portionToEdit;
        this.updatedPortion = updatedPortion;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidArgsException {
        Task updatedTask = taskList.editTask(taskIndex, portionToEdit, updatedPortion);
        return ui.printEditTask(updatedTask, taskIndex);
    }
}
