package service;

import model.Task;

import java.util.ArrayList;

public class TaskList{

    private ArrayList<Task> todos;

    public TaskList() {
        this.todos = new ArrayList<Task>();
    }

    public Task get(int i) {
        return todos.get(i);
    }

    public Task remove(int i) {
        return todos.remove(i);
    }

    public int size() {
        return todos.size();
    }
    public void add(Task newTask) {
       todos.add(newTask);
    }
}
