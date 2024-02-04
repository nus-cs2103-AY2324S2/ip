package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

import duke.exception.InvalidArgumentException;

/**
 * Represents the command to mark a task in the taskList.
 */
public class MarkCommand extends Command {
    private int positionToMark;

    /**
     * Constructor for a mark task command,
     * which initialises the command with the position (in the taskList) of the task
     * to be marked.
     *
     * @param description String value of the position of task to be marked.
     */
    public MarkCommand(String description) {
        this.positionToMark = Integer.parseInt(description);
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException {
        try {
            taskList.markTask(positionToMark);
            ui.displayMarkedTask(taskList, positionToMark);
            Storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("The index is out of range >.<");
        }

    }
}