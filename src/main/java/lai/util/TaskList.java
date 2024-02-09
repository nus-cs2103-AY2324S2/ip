package lai.util;

import lai.task.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<Task> {
    protected List<Task> tasks = new ArrayList<>();

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> add(Task newTask, Storage storage) {
        tasks.add(newTask);
        storage.updateTasksFile(this);

        Ui.printTaskAdded(newTask, this);

        return tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public boolean remove(Task t) {
        return this.tasks.remove(t);
    }

    @Override
    public Iterator<Task> iterator() {
        return this.tasks.iterator();
    }
}
