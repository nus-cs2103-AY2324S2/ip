package hal.command;

import hal.task.TaskList;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.markAsUndone(taskIndex);
    }
}
