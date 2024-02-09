package lai.util;

import lai.task.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<Task> {
    List<Task> tasks = new ArrayList<>();

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> add(Task newTask, Storage storage) {
        this.tasks.add(newTask);
        storage.updateTasksFile(this);

        Ui.printTaskAdded(newTask, this);

        return this.tasks;
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

    public List<Task> find(String query) throws LaiException {
        List<Task> result  = new ArrayList<>();
        for (Task task : this.tasks) {
            int index = task.getDescription().indexOf(query);
            if (index != -1) {
                result.add(task);
            }
        }

        return result;
    }

    @Override
    public Iterator<Task> iterator() {
        return this.tasks.iterator();
    }
}
