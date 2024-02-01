package pyrite.command;

import pyrite.StateFile;
import pyrite.task.Task;
import pyrite.TaskList;

public class StatusCommand extends Command{
    int id;
    private Task.Status targetStatus;
    public StatusCommand(int id, Task.Status targetStatus) {
        this.id = id;
        this.targetStatus = targetStatus;
    }
    @Override
    public String execute(TaskList tasks, StateFile file) {
        if (!tasks.isValidId(this.id)){
            return "pyrite.task.Task to mark/unmark does not exist.";
        }
        tasks.setStatus(this.id, this.targetStatus);
        String saveResult = file.saveState(tasks);
        if (targetStatus == Task.Status.DONE) {
            return "Nice! I've marked this task as done:\n"
                    + "\t"
                    + tasks.toString(this.id)
                    + saveResult;
        } else if (targetStatus == Task.Status.NOT_DONE) {
            return "OK, I've marked this task as not done yet:\n"
                    + "\t"
                    + tasks.toString(this.id)
                    + saveResult;
        } else {
            return "Invalid Status";
        }

    }
}