package command;

import exceptions.DukeException;

import task.TaskList;
import task.Task;

import utilities.Ui;
import utilities.Storage;

public class DeleteCommand extends Command {
    /**
     * The task index of the task that is to be deleted.
     */
    private int taskToDeleteIndex;

    /**
     * DeleteCommand class constructor.
     * @param userInput The input that the user types into the command line.
     */
    public DeleteCommand(String userInput) {
        super(false);
        this.taskToDeleteIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
    }

    /**
     * Executes the delete task process.
     * @param taskList The task list that the task is deleted from.
     * @param storage The storage that the task list is stored in after the task is deleted.
     * @param ui Provides corresponding user output based on whether the process is successful or not.
     * @throws DukeException The exception thrown when the user input is invalid.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            Task removedTask = taskList.deleteTask(this.taskToDeleteIndex);
            ui.showDelete(removedTask);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Invalid input. Please provide a valid task index or check that the task exists.");
        }
        storage.save(taskList);
        ui.showTaskListLength(taskList);
    }
}
