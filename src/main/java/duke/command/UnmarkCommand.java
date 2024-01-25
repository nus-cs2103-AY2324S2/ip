package duke.command;


import duke.common.Messages;
import duke.common.TaskList;
import duke.exception.InvalidInputException;
import duke.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.List;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_USAGE = "unmark: marks a task in the list as uncompleted by taking a numerical value input.\n" +
            "Example mark 1";
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        if (taskNumber <= 0 || taskNumber > taskList.getListSize()) {
            throw new InvalidInputException(
                    String.format(Messages.MESSAGE_INVALID_INPUT_VALUE, "you can only mark task that are listed"));
        }
        Task task = taskList.getTask(taskNumber - 1);
        task.setHasDone(false);
        ui.showMarkAsNotDone(task);
    }
}
