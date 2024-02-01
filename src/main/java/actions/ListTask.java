package actions;

import storage.Storage;
import tasks.Task;

public class ListTask {
    private Storage storage;

    public ListTask(Storage storage) {
        this.storage = storage;
    }

    public String list() {
        if (storage.load().isEmpty()) {
            return "List is Empty !";
        }
        String result = "";
        int count = 1;
        for (Task s : storage.load()) {
            result += count + ". " + s.toString() + "\n";
            count++;
        }
        return result;
    }
}
