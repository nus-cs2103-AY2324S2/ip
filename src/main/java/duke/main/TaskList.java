package duke.main;
import duke.task.*;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task t) {
        this.taskList.add(t);
    }

    public void remove(int index) {
        this.taskList.remove(index);
    }

    public void mark(int index) {
        this.taskList.get(index).mark();
    }

    public void unmark(int index) {
        this.taskList.get(index).unmark();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public int size() {
        return this.taskList.size();
    }
}
