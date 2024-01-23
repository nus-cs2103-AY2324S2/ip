package action;

import util.PrintUtil;
import util.Store;

public class List implements Action {
    private Store store;

    public List(Store store) {
        this.store = store;
    }

    public void execute() {
        PrintUtil.print("Here are the tasks in your list:\n" + this.store.toString());
    }
}
