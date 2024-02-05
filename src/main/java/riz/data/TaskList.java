package riz.data;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Task get(int i) {
        return this.taskList.get(i);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void remove(int i) {
        this.taskList.remove(i);
    }

    public int size() {
        return this.taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void clear() {
        this.taskList.clear();
    }

    public void find(String word) {
        System.out.println("Here are the tasks matching " + "\"" + word + "\"...");
        int count = 1;
        for (Task task : this.taskList) {
            if (task.find(word)) {
                System.out.print(count + ". ");
                System.out.println(task);
                count++;
            }
        }
    }

}
