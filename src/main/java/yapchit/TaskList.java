package yapchit;

import yapchit.tasks.Task;
import yapchit.yapchitexceptions.InvalidDetailException;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void delete(int idx) {
        this.tasks.remove(idx);
    }

    public void mark(int idx, boolean isDone) throws InvalidDetailException {
        if (idx >= tasks.size()) {
            throw new InvalidDetailException("Invalid item index, please try again");
        } else {
            tasks.get(idx).setDone(isDone);
        }
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public int getListSize() {
        return this.tasks.size();
    }

    public Task getItem(int i) {
        return this.tasks.get(i);
    }
}
