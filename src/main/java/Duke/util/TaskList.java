package duke.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import duke.tasks.Task;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public Task markTask(int index) {
        return tasks.get(index).markDone();
    }
    public Task unMarkTask(int index) {
        return tasks.get(index).unMarkDone();
    }
    public Task delete(int index) {
        return tasks.remove(index);
    }
    public Task addTask(Task t) {
        tasks.add(t);
        return t;
    }
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    public ArrayList<Task> findTaskWithDate(LocalDateTime dateToFind) {
        ArrayList<Task> res = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).hasDate(dateToFind)) {
                res.add(tasks.get(i));
            }
        }
        return res;
    }
    public ArrayList<Task> findTasksWithString(String stringToFind) {
        ArrayList<Task> res = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (tasks.get(i).descriptionHasWord(stringToFind)) {
                res.add(tasks.get(i));
            }
        }
        return res;
    }
    public int getNumberOfTasks() {
        return this.tasks.size();
    }
}
