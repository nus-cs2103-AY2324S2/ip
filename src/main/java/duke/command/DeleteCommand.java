package duke.command;


import duke.common.Messages;
import duke.common.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_USAGE = "delete: delete a task on the list.\n" +
                                                "Example: delete 1";
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskNumber <= 0 || taskNumber > taskList.getListSize()) {
            throw new InvalidInputException(
                    String.format(Messages.MESSAGE_INVALID_INPUT_VALUE, "you can only mark task that are listed"));
        }
        Task task = taskList.getTask(taskNumber - 1);
        taskList.deleteTask(taskNumber - 1);
        ui.showDeleteTask(task, taskList);
    }
}
