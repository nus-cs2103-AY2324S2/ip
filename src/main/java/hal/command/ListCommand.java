package hal.command;

import hal.task.TaskList;

public class ListCommand extends Command {
    private String task;

    public ListCommand() {
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.listTasks();
    }
}
