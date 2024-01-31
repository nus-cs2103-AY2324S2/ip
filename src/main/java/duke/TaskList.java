package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean addTask(Task task) {
        if (task == null) {
            return false;
        }
        return tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("No task found at index " + index + ".");
        }
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task markDone(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task unmarkDone(int index) {
        Task task = tasks.get(index);
        task.markAsUndone();
        return task;
    }

    public TaskList findTasks(String description) {
        ArrayList<Task> filteredTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.getDescription().contains(description)) {
                filteredTasks.add(task);
            }
        }
        return new TaskList(filteredTasks);
    }
}
