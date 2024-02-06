package liv.processor;

import liv.exception.LivException;
import liv.task.Task;
import liv.container.TaskList;
import liv.ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) throws LivException {
        int trueIndex = index - 1;
        Task task = TaskList.getTask(trueIndex);
        boolean currentState = task.getStatus();
        if (!currentState) {
            throw new LivException("Mission already unmarked!");
        }
        task.markAsNotDone();
        Ui.displayUnmarkCommand(task);
    }
}
