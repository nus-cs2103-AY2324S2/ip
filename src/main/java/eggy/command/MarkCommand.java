package eggy.command;

import eggy.exception.EggyException;
import eggy.exception.IncompleteCommandException;
import eggy.exception.TaskListIndexOutOfBoundsException;
import eggy.exception.TaskNumberFormatException;
import eggy.storage.Storage;
import eggy.task.TaskList;
import eggy.task.Task;
import eggy.ui.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(String[] commands, int tasksSize) throws EggyException {
        if (commands.length < 2) {
            throw new IncompleteCommandException("mark");
        }
        try {
            int taskNumber = Integer.parseInt(commands[1]);
            if (taskNumber < 1 || taskNumber > tasksSize) {
                throw new TaskListIndexOutOfBoundsException(taskNumber, tasksSize);
            }
            this.index = taskNumber - 1;
        } catch (NumberFormatException e) {
            throw new TaskNumberFormatException();
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EggyException {
        Task task = tasks.getTask(index);
        task.markDone();
        ui.printTaskMarkedDone(task);
        storage.save(tasks);
    }
}
