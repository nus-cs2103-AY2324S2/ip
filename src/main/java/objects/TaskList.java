package objects;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> implements Serializable {
    public void addTask(Task task) {
        this.add(task);
    }

    public void markTask(int i) {
        Task task = this.get(i);

        if (task != null) {
            task.mark();
        }
    }

    public void unmarkTask(int i) {
        Task task = this.get(i);

        if (task != null) {
            task.unmark();
        }
    }
}
