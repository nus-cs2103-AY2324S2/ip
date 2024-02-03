package podz.command;

import podz.task.Deadline;
import podz.ui.Ui;

public class DeadlineCommand extends Command {
    private Deadline deadline;

    public DeadlineCommand (Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(Ui ui) {
        super.taskList.addTask(deadline);
        ui.printToUser("\tGot it. I've added this task:",
                        "\t" + this.deadline,
                        super.taskList.getListCounter());
    }
}
