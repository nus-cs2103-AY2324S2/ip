package gulie;
import gulie.task.Task;

import java.util.ArrayList;
public class GulieTasklist extends ArrayList<Task> {

    public Task store(Task task) {
        add(task);
        return task;
    }

    public Task delete(int i) throws GulieException {
        if (i >= size() || i < 0) {
            throw new GulieException("Invalid index: " + i);
        }
        Task task = get(i);
        return remove(i);
    }

    public void mark(int i) throws GulieException {
        if (i >= size() || i < 0)
            throw new GulieException("Invalid index: " + i);
        Task task = get(i);
        task.setMark(true);
    }

    public void unmark(int i) throws GulieException {
        if (i >= size() || i < 0)
            throw new GulieException("Invalid index: " + i);
        Task task = get(i);
        task.setMark(false);
    }
}
