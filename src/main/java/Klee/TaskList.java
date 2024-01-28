package Klee;

import Klee.task.Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList () {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int i) {
        tasks.remove(i);
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == TaskList.class) {
            try {
                if (this.size() == ((TaskList) obj).size()) {
                    for (int i = 0; i < this.size(); i++) {
                        if (this.get(i) == ((TaskList) obj).get(i)) return false;
                    }
                    return true;
                } else return false;
            } catch (Exception e) {
                return false;
            }
        } else return false;
    }
}
