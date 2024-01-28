package action;
import java.io.IOException;

import task.Task;
import util.PrintUtil;
import util.TaskList;

public class Delete implements Action {
    private final TaskList taskList;
    private final int idx;

    public Delete(TaskList taskList, int idx) {
        this.taskList = taskList;
        this.idx = idx;
    }

    @Override
    public void execute() throws IOException {
        Task task = this.taskList.delete(this.idx);
        int size = this.taskList.getSize();
        String plural = size == 1 ? "task" : "tasks";
        PrintUtil.print("Got it! Task deleted:\n  " + task + "\nNow you have "
                + size + " " + plural + " remaining.");
    }
}
