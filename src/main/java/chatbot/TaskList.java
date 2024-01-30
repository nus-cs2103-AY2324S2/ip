package chatbot;

import chatbot.exceptions.AlreadyMarkedException;
import chatbot.exceptions.AlreadyUnmarkedException;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(Storage store) throws IOException, ClassNotFoundException {
        this.tasks = store.readFromStore();
    }

    public int getSize() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i+1, tasks.get(i)));
        }
        return sb.toString();
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public void markTask(int i) throws AlreadyMarkedException {
        this.tasks.get(i).mark();
    }

    public void unmarkTask(int i) throws AlreadyUnmarkedException {
        this.tasks.get(i).unmark();
    }

    public Task removeTask(int i) {
        return this.tasks.remove(i);
    }

    public void saveToStore(Storage store) throws IOException {
        store.saveToStore(this.tasks);
    }
}
