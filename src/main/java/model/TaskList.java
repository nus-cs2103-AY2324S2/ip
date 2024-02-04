package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 * The {@code TaskList} class represents a list of tasks.
 * 
 * <p> This class inherits {@code Serializable} for storage.
 * 
 * <p> Contains an {@code ArrayList<Task>}.
 */
public class TaskList implements Serializable {
    private ArrayList<Task> tasks;

    /**
     * Constructor to initialise a new empty {@code TaskList}.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Overloaded Method to add a {@code ToDo} task.
     * 
     * @param name Name of the "To Do" task.
     * @return {@code ToDo} task added to this {@code TaskList}.
     */
    public Task addTask(String name) {
        Task t = new ToDo(name);
        tasks.add(t);
        return t;
    }

    /**
     * Overloaded Method to add a {@code Deadline} task.
     * 
     * @param name Name of the deadline task.
     * @param deadline {@code LocalDateTime} object representing the deadline.
     * @return {@code Deadline} task added to this {@code TaskList}.
     */
    public Task addTask(String name, LocalDateTime deadline) {
        Task t = new Deadline(name, deadline);
        tasks.add(t);
        return t;
    }

    /**
     * Overloaded Method to add an {@code Event} task.
     * 
     * @param name Name of the event task.
     * @param start {@code LocalDateTime} object representing the start of the event.
     * @param end {@code LocalDateTime} object representing the end of the event.
     * @return {@code Event} task added to this {@code TaskList}.
     */
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
