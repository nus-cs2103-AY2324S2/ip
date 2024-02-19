package hal.command;

import hal.task.TaskList;

public class AddCommand extends Command {
    private String task;

    public AddCommand(String task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.addTask(task);
    }
}