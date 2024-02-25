package service;

import model.Task;

import java.util.ArrayList;

public class TaskList{

    private ArrayList<Task> todos;

    public TaskList() {
        this.todos = new ArrayList<Task>();
    }

    /**
     * Obtains the task with the corresponding index number.
     * @param i the index number.
     * @return task with the corresponding index number.
     */
    public Task get(int i) {
        return todos.get(i);
    }

    /**
     * Returns the task with the corresponding index number.
     * @param i the index number.
     * @return task that was removed.
     */
    public Task remove(int i) {
        return todos.remove(i);
    }

    /**
     * Returns number of items in the tasklist
     * @return number of items in the tasklist
     */
    public int size() {
        return todos.size();
    }

    public void add(Task newTask) {
       todos.add(newTask);
    }
}
