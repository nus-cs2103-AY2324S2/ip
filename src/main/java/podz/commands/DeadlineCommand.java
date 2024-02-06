package podz.commands;

import podz.task.Deadline;
import podz.ui.Ui;

/**
 * Represents a command to add a deadline task to the task manager.
 */
public class DeadlineCommand extends Command {
    private Deadline deadline;

    /**
     * Constructs a DeadlineCommand with the specified Deadline task.
     * 
     * @param deadline the deadline task to be added
     */
    public DeadlineCommand (Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Executes the deadline command to add the Deadline task to the task manager.
     */
    @Override
    public void execute(Ui ui) {
        super.taskList.addTask(deadline);
        ui.printToUser("\tGot it. I've added this task:",
                        "\t" + this.deadline,
                        super.taskList.getListCounter());
    }
}
