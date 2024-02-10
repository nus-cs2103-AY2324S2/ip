package duke.task;

import java.util.ArrayList;

/**
 * A TaskList class that encapsulates the information and actions of a task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    public TaskList(TaskList other) {
        this.tasks = new ArrayList<>(other.tasks);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int taskIndex) {
        Task taskToDelete = tasks.get(taskIndex);
        this.tasks.remove(taskIndex);
        return taskToDelete;
    }

    public int getNoOfTasks() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void clearTasks() {
        this.tasks = new ArrayList<>();
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    @Override
    public String toString() {
        // Creating a string builder to append task index and details
        StringBuilder listContent = new StringBuilder();
        // Iterating through the task list
        for (int i = 0; i < this.getNoOfTasks(); i++) {
            // Appending task index and task details
            listContent.append(i + 1).append(".").append(this.getTask(i)).append("\n");
        }
        // Return the final string representation of the task list
        return String.valueOf(listContent);
    }
}
