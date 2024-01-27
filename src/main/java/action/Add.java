package action;

import task.Task;
import util.PrintUtil;
import util.TaskList;

import java.io.*;

public class Add implements Action {
    private Task task;
    private TaskList taskList;

    public Add(Task task, TaskList taskList) {
        this.task = task;
        this.taskList = taskList;
    }

    @Override
    public void execute() throws IOException {
        this.taskList.add(this.task);
        int size = this.taskList.getSize();
        String plural = size == 1 ? "task" : "tasks";
        PrintUtil.print("Got it! Task added:\n  " + this.task + "\nNow you have " +
                size + " outstanding " + plural + ".");
    }
}
