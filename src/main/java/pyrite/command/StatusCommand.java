package pyrite.command;

import pyrite.StateFile;
import pyrite.TaskList;
import pyrite.task.Task;

/**
 * Command to change the status of a task.
 */
public class StatusCommand extends Command {
    private int id;
    private Task.Status targetStatus;

    /**
     * Constructs a StatusCommand.
     *
     * @param id Index of task to be updated.
     * @param targetStatus Status to be applied.
     */
    public StatusCommand(int id, Task.Status targetStatus) {
        this.id = id;
        this.targetStatus = targetStatus;
    }

    /**
     * {inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, StateFile file) {
        if (!tasks.isValidId(this.id)) {
            return "Task to mark/unmark does not exist.";
        }
        tasks.setStatus(this.id, this.targetStatus);
        String saveResult = file.saveState(tasks);
        if (targetStatus == Task.Status.Done) {
            return "Nice! I've marked this task as done:\n"
                    + "\t"
                    + tasks.toString(this.id)
                    + saveResult;
        } else if (targetStatus == Task.Status.NotDone) {
            return "OK, I've marked this task as not done yet:\n"
                    + "\t"
                    + tasks.toString(this.id)
                    + saveResult;
        } else {
            return "Invalid Status";
        }

    }
}
