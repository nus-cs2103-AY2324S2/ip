package liv.processor;

import liv.task.Deadline;
import liv.container.TaskList;
import liv.ui.Ui;

public class DeadlineCommand extends Command {
    private Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(deadline);
        Ui.displayDeadlineCommand(deadline);
    }

    @Override
    public boolean changedData() {
        return true;
    }
}
