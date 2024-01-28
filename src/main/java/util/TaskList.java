package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import task.Task;

public class TaskList {
    private static ArrayList<Task> tasks;
    private final Storage storage;

    public TaskList() throws IOException {
        this.tasks = new ArrayList<>();
        this.storage = new Storage(tasks);
    }

    private TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    public void add(Task t) throws IOException {
        this.tasks.add(t);
        storage.writeToFile(tasks);
    }

    public Task delete(int i) throws IOException {
        Task t = this.tasks.get(i - 1);
        this.tasks.remove(i - 1);
        storage.writeToFile(tasks);
        return t;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task mark(int i) throws IOException {
        Task t = this.tasks.get(i - 1).mark();
        storage.writeToFile(tasks);
        return t;
    }

    public Task unmark(int i) throws IOException {
        Task t = this.tasks.get(i - 1).unmark();
        storage.writeToFile(tasks);
        return t;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public static List<Task> find(String s) {
        return tasks.stream()
                .filter(t -> t.contains(s))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append(i + 1).append(".").append(this.tasks.get(i).toString());
            if (i != this.tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
