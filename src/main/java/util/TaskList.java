package util;

import java.io.*;
import java.lang.StringBuilder;
import java.util.ArrayList;

import task.Task;

public class TaskList {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private final Storage storage;

    public TaskList() throws IOException {
        this.storage = new Storage(tasks);
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
