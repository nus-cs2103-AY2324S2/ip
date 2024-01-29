package kervyn.Commands;

import kervyn.Tasks.TaskList;

public class ListCommand extends Command {
    public ListCommand(TaskList taskList) {
        super("List", taskList);
    }

    @Override
    public void executeCommand() {
        taskList.listTasks(taskList.getTaskList());
    }
}
