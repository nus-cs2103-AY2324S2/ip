package linus;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void removeTask(int index) {
        this.taskList.remove(index);
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public int getSize() {
        return this.taskList.size();
    }

    // rename needed below
    public void addAll(ArrayList<Task> taskList) {
        this.taskList.addAll(taskList);
    }

    public ArrayList<Task> getAllTasks() {
        return this.taskList;
    }

    public ArrayList<Task> findMatchingTasks(String findKeyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getDescription().contains(findKeyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}
