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
     * Creates a new empty {@code TaskList}.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    private Task addTask(Task t) {
        tasks.add(t);
        return t;
    }

    /**
     * Adds a {@code ToDo}, {@code Deadline} or {@code Event} task.
     * 
     * <p> If {@code 0 LocalDateTime} is provided, creates a {@code ToDo} task.
     * 
     * <p> If {@code 1 LocalDateTime} is provided, creates a {@code Deadline} task.
     * 
     * <p> If {@code >=2 LocalDateTime} are provided, creates an {@code Event} task.
     * Ignores any LocalDateTime supplied after 2.
     * 
     * @param name Name of the deadline task.
     * @param dateTimes {@code LocalDateTime} objects representing the task.
     * @return The task added to this {@code TaskList}.
     */
    public Task addTask(String name, LocalDateTime ... dateTimes) {
        Task t;
        if (dateTimes.length == 0) {
            t = new ToDo(name);
        } else if (dateTimes.length == 1) {
            t = new Deadline(name, dateTimes[0]);
        } else {
            t = new Event(name, dateTimes[0], dateTimes[1]);
        }
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

    public TaskList match(String s) {
        TaskList result = new TaskList();
        for (Task t : tasks) {
            if (t.nameContains(s)) {
                result.addTask(t);
            }
        }
        return result;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        if (tasks.size() > 0) {
            s.append("1.").append(tasks.get(0));
        }
        for (int i = 1; i < tasks.size(); i++) {
            s.append("\n").append(i + 1).append(".").append(tasks.get(i));
        }
        return s.toString();
    }
}
