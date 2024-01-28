package action;

import java.util.List;

import task.Task;
import util.PrintUtil;
import util.TaskList;

public class Find implements Action {
    private final String s;
    public Find(String s) {
        this.s = s;
    }

    @Override
    public void execute() {
        List<Task> list = TaskList.find(s);
        PrintUtil.printList(list);
    }
}
