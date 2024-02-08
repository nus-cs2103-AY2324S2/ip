package luke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    public TaskList() {
        list = new ArrayList<>();

    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void add(Task task) {
        list.add(task);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void markTask(int index) {
        list.get(index).markAsDone();
    }

    public void unmarkTask(int index) {
        list.get(index).markAsUndone();
    }

    public TaskList find(String keyword) {
        TaskList foundList = new TaskList();
        for (Task task : list) {
            if (task.matchKeyword(keyword)) {
                foundList.add(task);
            }
        }
        return foundList;
    }

}
