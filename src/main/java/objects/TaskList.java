package objects;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task>{
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
