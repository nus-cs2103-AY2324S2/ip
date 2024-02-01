package felix.command;

import felix.exception.FelixException;
import felix.task.Task;
import felix.utils.TaskList;
import felix.utils.Ui;
import felix.utils.Storage;
public class MarkCommand extends Command {
    private final int indexToMark;
    public MarkCommand(int indexToMark) {
        this.indexToMark = indexToMark;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        try {
            Task task = tasks.getTask(this.indexToMark);
            task.markAsDone();
            ui.println("Nice! I have marked this task as done:");
            ui.println(task);
            storage.writeToFile(tasks);
        } catch (IndexOutOfBoundsException err) {
            throw new FelixException(String.format("You have %d tasks, provide a valid index in the range [1,%d]",
                    tasks.getCount(), tasks.getCount()));
        }
    }
}
