package haro.command;

import haro.Storage;
import haro.TaskList;
import haro.Ui;
import haro.exception.InvalidArgsException;
import haro.task.Task;

import java.io.StringReader;

public class EditCommand extends Command {
    private int taskIndex;
    private String portionToEdit;
    private String updatedPortion;
    public EditCommand(int taskIndex,String portionToEdit, String updatedPortion) {
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
