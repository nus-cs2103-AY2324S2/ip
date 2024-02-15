package tommy.command;

import tommy.Storage;
import tommy.Ui;
import tommy.exception.InvalidArgumentException;
import tommy.task.Task;
import tommy.task.TaskList;

/**
 * Represents the command to delete a task from the taskList.
 */
public class DeleteCommand extends Command {

    private int positionToDelete;

    // TODO : consider catching exception for invalid string which isn't a number
    /**
     * Constructor for a delete task command,
     * which initialises the command with the position (in the taskList) of the task
     * to be deleted.
     *
     * @param description String value of the position of task to be deleted.
     */
    public DeleteCommand(String description) {
        this.positionToDelete = Integer.parseInt(description);
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException {
        try {
            Task taskToDelete = taskList.getTaskAtPosition(positionToDelete);
            taskList.deleteTaskAtPosition(positionToDelete);
            return ui.displayDeletedTask(taskList, taskToDelete);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("The index is out of range >.<");
        }
    }
}
