package actions;

import storage.Storage;


public class UnmarkTask {
    private Storage storage;
    private int index;

    public UnmarkTask(Storage storage, int index) {
        this.storage = storage;
        this.index = index;
    }

    public String unmark() {
        storage.load().get(index - 1).unmark();
        String temp = "OK, I've marked this task as not done yet: \n";
        temp += storage.load().get(index - 1).toString();
        return temp;
    }
}
