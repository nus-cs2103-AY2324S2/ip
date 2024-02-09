package action;

import util.TaskList;

public class Sort implements Action {
    private TaskList taskList;
    public Sort(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        TaskList.sort();
        return "Tasks sorted!\nHere are the tasks in your list:\n" + taskList.toString();
    }
}
