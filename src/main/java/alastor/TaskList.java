package alastor;

import alastor.task.Task;

import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns a new TaskList containing tasks that match the keyword.
     *
     * @param keyword The keyword to search for.
     * @return A new TaskList containing tasks that match the keyword.
     */
    public TaskList find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.isMatchingKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }
        return new TaskList(matchingTasks);
    }

    public void delete(int index) {
        this.tasks.remove(index);
    }
}
