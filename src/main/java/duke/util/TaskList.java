package duke.util;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void add(Task t) {
        this.list.add(t);
    }

    public int getSize() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public Task delete(int num) {
        return this.list.remove(num);
    }

    public String print() {
        int count = 1;
        StringBuilder sb = new StringBuilder("\t Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append("\t\t " + count + "." + list.get(i).printTask() + (i == list.size() - 1 ? "" : "\n"));
            count++;
        }
        return sb.toString();
    }

    public void setList(ArrayList<Task> list) {
        this.list = list;
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}
