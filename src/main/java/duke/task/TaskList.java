package duke.task;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int index) {
        tasks.remove(index - 1);
    }

    public void list() {
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public void mark(int index) {
        tasks.get(index - 1).markAsDone();
    }

    public void unmark(int index) {
        tasks.get(index - 1).markAsUndone();
    }

    public int getTaskSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String getTaskDescription(int index) {
        return this.tasks.get(index).description;
    }
}
