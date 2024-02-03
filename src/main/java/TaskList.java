import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> tasks;

    TaskList() {
        tasks = new ArrayList<>();
    }

    int size() {
        return tasks.size();
    }

    // Overloaded Method for ToDo task
    Task addTask(String name) {
        Task t = new ToDo(name);
        tasks.add(t);
        return t;
    }

    // Overloaded Method for Deadline task
    Task addTask(String name, LocalDateTime deadline) {
        Task t = new Deadline(name, deadline);
        tasks.add(t);
        return t;
    }

    // Overloaded Method for Event task
    Task addTask(String name, LocalDateTime start, LocalDateTime end) {
        Task t = new Event(name, start, end);
        tasks.add(t);
        return t;
    }

    Task deleteTask(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    Task mark(int index) throws IndexOutOfBoundsException {
        Task t = tasks.get(index).mark();
        tasks.set(index, t);
        return t;
    }

    Task unmark(int index) throws IndexOutOfBoundsException {
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
