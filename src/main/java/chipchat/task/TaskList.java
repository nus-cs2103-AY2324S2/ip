package chipchat.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> initTasks) {
        this.tasks = initTasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    public void mark(int index) {
        this.tasks.get(index).mark();
    }

    public void unmark(int index) {
        this.tasks.get(index).unmark();
    }

    public String printTask(int index) {
        return this.tasks.get(index).toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            str.append(String.format("%d. %s\n", i, tasks.get(i)));
        }
        return str.toString();
    }

    public String dataString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            str.append(String.format("%d. %s\n", i, tasks.get(i).dataString()));
        }
        return str.toString();
    }
}
