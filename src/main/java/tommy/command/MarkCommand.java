package tommy.command;

import tommy.Storage;
import tommy.Ui;
import tommy.exception.InvalidArgumentException;
import tommy.task.TaskList;

/**
 * Represents the command to mark a task in the taskList.
 */
public class MarkCommand extends Command {

    private int positionToMark;
    private static final String indexOutOfRangeWarning = "The index is out of range >.<";


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
    public String execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException {
        assert positionToMark <= taskList.getSize() && positionToMark > 0 : "The position to mark should be within the size of the taskLis.";
        try {
            taskList.markTask(positionToMark);
            Storage.save(taskList);
            return ui.displayMarkedTask(taskList, positionToMark);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException(indexOutOfRangeWarning);
        }
    }
}
