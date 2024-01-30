package Command;
import Utilities.Ui;
import Utilities.Storage;
import Task.TaskList;
import Exceptions.DukeException;
import Task.Task;

public class DeleteCommand extends Command {
    private int taskToDeleteIndex;
    public DeleteCommand(String userInput) {
        super(false);
        this.taskToDeleteIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            Task removedTask = taskList.deleteTask(this.taskToDeleteIndex);
            ui.showDelete(removedTask);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid input. Please provide a valid task index or check that the task exists.");
        }
        storage.save(taskList);
        ui.showTaskListLength(taskList);
    }
}
