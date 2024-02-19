package hal.command;

import hal.task.TaskList;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.markAsDone(taskIndex);
    }
}
