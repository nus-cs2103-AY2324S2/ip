package tommy.command;

import tommy.Ui;
import tommy.Storage;

import tommy.task.TaskList;

import tommy.exception.InvalidArgumentException;

/**
 * Represents the command to mark a task in the taskList.
 */
public class UnmarkCommand extends Command {

    private int positionToUnmark;

    /**
     * Constructor for an unmark task command,
     * which initialises the command with the position (in the taskList) of the task
     * to be marked.
     *
     * @param description String value of the position of task to be unmarked.
     */
    public UnmarkCommand(String description) {
        this.positionToUnmark = Integer.parseInt(description);
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException {
        try {
            taskList.unmarkTask(positionToUnmark);
            Storage.save(taskList);
            return ui.displayUnmarkedTask(taskList, positionToUnmark);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("The index is out of range >.<");
        }
    }
}