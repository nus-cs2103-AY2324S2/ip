package jade.data;

import jade.data.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList{
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void remove(int index) {
        taskList.remove(index);
    }

    public void mark(int index) {
        taskList.get(index).mark();
    }

    public void unmark(int index) {
        taskList.get(index).unmark();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public String listFormatter() {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            sb.append(task.taskFormatter());
        }
        return sb.toString();
    }
 }
