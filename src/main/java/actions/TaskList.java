package actions;

import task.Task;

import java.util.ArrayList;
/**
 * Tasklist is a class that handles the possible actions on a list of tasks
 */
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    protected ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getArraySize() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void markTask(Task task) {
        task.markAsDone();
    }

    public void unmarkTask(Task task) {
        task.unmark();
    }

    public List<Task> find(String input) {
        return this.tasks.stream()
                .filter(task-> task.toString().toLowerCase().contains(input.toLowerCase()))
                .collect(Collectors.toList());
    }


}
