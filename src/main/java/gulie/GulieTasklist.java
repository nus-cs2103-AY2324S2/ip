package gulie;
import gulie.task.Task;

import java.util.ArrayList;
public class GulieTasklist extends ArrayList<Task> {

    public Task store(Task task) {
        this.add(task);
        return task;
    }

    public Task delete(int i) throws GulieException {
        if (i >= this.size() || i < 0) {
            throw new GulieException("Invalid index: " + i);
        }
        Task task = this.get(i);
        return this.remove(i);
    }

    public void mark(int i) throws GulieException {
        if (i >= this.size() || i < 0)
            throw new GulieException("Invalid index: " + i);
        Task task = this.get(i);
        task.setMark(true);
    }

    public void unmark(int i) throws GulieException {
        if (i >= this.size() || i < 0)
            throw new GulieException("Invalid index: " + i);
        Task task = this.get(i);
        task.setMark(false);
    }

    public GulieTasklist find(String keyword) {
        GulieTasklist tasklist = new GulieTasklist();
        for (Task task: this) {
            if (task.hasKeyword(keyword)) {
                tasklist.store(task);
            }
        }
        return tasklist;
    }
}
