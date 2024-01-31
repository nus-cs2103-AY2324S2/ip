package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;
import duke.exception.NoTaskFoundException;
import duke.task.Task;

public class MarkCommand extends Command {
    private String arguments;

    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws InvalidArgumentException, NoTaskFoundException {
        try {
            int taskId = Integer.parseInt(this.arguments);
            Task task = taskList.get(taskId);
            task.changeMark("MARK");
            ui.markTask(task);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("MARK");

        } catch (IndexOutOfBoundsException e) {
            throw new NoTaskFoundException(this.arguments);
        }
    }
}
