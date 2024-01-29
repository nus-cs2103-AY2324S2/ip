package run;

import others.BelleException;
import tasks.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Constructs TaskList.
     */
    public TaskList(ArrayList<Task> l) {
        this.list = l;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public Task getTask(int i) {
        return list.get(i);
    }

    /**
     * Removes task at a specific index from
     * TaskList.
     *
     * @param  i Index of item to remove.
     */
    public void removeTask(int i) {
        list.remove(i);
    }

    public int getSize() {
        return list.size();
    }

    /**
     * Add task to TaskList.
     *
     * @param t Task to add.
     */
    public void addTask(Task t) {
        list.add(t);
    }
}


//mark and unmark greeting

//delte grreeting

//add greeting