package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    // Overloaded Method for ToDo task
    public Task addTask(String name) {
        Task t = new ToDo(name);
        tasks.add(t);
        return t;
    }

    // Overloaded Method for Deadline task
    public Task addTask(String name, LocalDateTime deadline) {
        Task t = new Deadline(name, deadline);
        tasks.add(t);
        return t;
    }

    // Overloaded Method for Event task
    public Task addTask(String name, LocalDateTime start, LocalDateTime end) {
        Task t = new Event(name, start, end);
        tasks.add(t);
        return t;
    }

    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    public Task mark(int index) throws IndexOutOfBoundsException {
        Task t = tasks.get(index).mark();
        tasks.set(index, t);
        return t;
    }

    public Task unmark(int index) throws IndexOutOfBoundsException {
        Task t = tasks.get(index).unmark();
        tasks.set(index, t);
        return t;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        if (tasks.size() > 0) {
            s.append("1.").append(tasks.get(0));
        }
        for (int i = 1; i < tasks.size(); i++) {
            s.append("\n").append(i+1).append(".").append(tasks.get(i));
        }
        return s.toString();
    }
}
