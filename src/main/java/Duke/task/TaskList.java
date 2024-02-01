package duke.task;

import java.util.ArrayList;
import java.util.List;
public class TaskList {
    private List<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    public TaskList(List<Task> t) {
        this.listOfTasks = t;
    }

    public void addTask(Task t) {
        this.listOfTasks.add(t);
    }

    public Task removeIndex(int index) {
        return this.listOfTasks.remove(index);
    }

    public void markTask(int index) {
        this.listOfTasks.get(index).markAsDone();
    }

    public void unmarkTask(int index) {
        this.listOfTasks.get(index).unmarkAsDone();
    }

    public int size() {
        return this.listOfTasks.size();
    }

    public Task get(int index) {
        return this.listOfTasks.get(index);
    }


}