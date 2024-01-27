package action;

import util.PrintUtil;
import util.TaskList;

public class List implements Action {
    private TaskList taskList;

    public List(TaskList taskList) {
        this.taskList = taskList;
    }

    public void execute() {
        PrintUtil.print("Here are the tasks in your list:\n" + this.taskList.toString());
    }
}
