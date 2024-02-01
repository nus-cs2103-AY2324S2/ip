package actions;

import storage.Storage;


public class MarkTask {
    private Storage storage;
    private int index;

    public MarkTask(Storage storage, int index) {
        this.storage = storage;
        this.index = index;
    }

    public String mark() {
        storage.load().get(index - 1).mark();
        String temp = "Nice! I've marked this task as done: \n";
        temp += storage.load().get(index - 1).toString();
        return temp;
    }
}
