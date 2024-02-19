package hal.command;

import hal.task.TaskList;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.removeTask(taskIndex);
    }
}
