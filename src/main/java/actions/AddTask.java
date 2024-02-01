package actions;

import tasks.Task;
import storage.Storage;

public class AddTask {
    private Storage storage;
    private Task t;

    public AddTask(Storage storage, Task t) {
        this.storage = storage;
        this.t = t;
    }

    public void add() {
        this.storage.load().add(t);
    }

    public String toString() {
        String temp = "Got it. I've added this task: \n";
        temp += " " + this.storage.load().get(this.storage.load().size() - 1).toString();
        temp += "\nNow you have " + this.storage.load().size() + " tasks in the list.";
        return temp;
    }
}
