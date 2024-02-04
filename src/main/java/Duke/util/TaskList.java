package Duke.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import Duke.tasks.Task;

public class TaskList {
    private ArrayList<Task> items;
    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }
    public Task markTask(int index) {
        return items.get(index).markDone();
    }
    public Task unMarkTask(int index) {
        return items.get(index).unMarkDone();
    }
    public Task delete(int index) {
        return items.remove(index);
    }
    public Task addTask(Task t) {
        items.add(t);
        return t;
    }
    public ArrayList<Task> list() {
        return this.items;
    }
    public ArrayList<Task> findTaskWithDate(LocalDateTime toFind) {
        ArrayList<Task> res = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).hasDate(toFind)) {
                res.add(items.get(i));
            }
        }
        return res;
    }
    public ArrayList<Task> findTasksWithString(String toFind) {
        ArrayList<Task> res = new ArrayList<>();
        for (int i = 0; i < this.items.size(); i++) {
            if (items.get(i).descriptionHasWord(toFind)) {
                res.add(items.get(i));
            }
        }
        return res;
    }
}
