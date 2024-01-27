package action;

import task.Task;
import util.PrintUtil;
import util.Store;

import java.io.*;

public class Add implements Action {
    private Task task;
    private Store store;

    public Add(Task task, Store store) {
        this.task = task;
        this.store = store;
    }

    @Override
    public void execute() throws IOException {
        this.store.add(this.task);
        int size = this.store.getSize();
        String plural = size == 1 ? "task" : "tasks";
        PrintUtil.print("Got it! Task added:\n  " + this.task + "\nNow you have " +
                size + " outstanding " + plural + ".");
    }
}
