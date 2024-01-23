package action;

import task.Task;
import util.Store;
import util.PrintUtil;

public class Delete implements Action {
    private Store store;
    private int idx;

    public Delete(Store store, int idx) {
        this.store = store;
        this.idx = idx;
    }

    @Override
    public void execute() {
        Task task = this.store.delete(this.idx);
        int size = this.store.getSize();
        String plural = size == 1 ? "task" : "tasks";
        PrintUtil.print("Got it! Task deleted:\n  " + task + "\nNow you have " +
                size + " " + plural + " remaining.");
    }
}
