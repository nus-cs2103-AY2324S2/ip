package actions;

import storage.Storage;

public class DeleteTask {
    private Storage storage;
    private int index;
    private String task;

    public DeleteTask(Storage storage, int index) {
        this.storage = storage;
        this.index = index;
        this.task = "";
    }

    public void delete() {
        this.task = storage.load().get(index - 1).toString();
        storage.load().remove(index - 1);
    }

    @Override
    public String toString() {
        String temp = "Noted. I've removed this task: \n";
        temp += " " + this.task;
        temp += "\nNow you have " + storage.load().size() + " tasks in this list.";
        return temp;
    }
}



