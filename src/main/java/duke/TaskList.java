package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCounter;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCounter = 0;
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskCounter++;
    }

    public Task deleteTask(int index) throws DukeException {
        validateIndex(index);
        Task removedTask = tasks.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask.getStatusIcon());
        System.out.println("Now you have " + tasks.size() + " " + (tasks.size() <= 1 ? "task" : "tasks") + " in the list.");
        return removedTask;
    }

    public Task getTask(int index) throws DukeException {
        validateIndex(index);
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    private void validateIndex(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Task not found. Please provide a valid task number.");
        }
    }

    public void markTaskAsDone(int index) throws DukeException {
        validateIndex(index);
        Task task = tasks.get(index);
        if (task.isDone()) {
            throw new DukeException("Oops! This task is already marked as done.");
        }
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.getStatusIcon());
    }

    public void markTaskAsNotDone(int index) throws DukeException {
        validateIndex(index);
        Task task = tasks.get(index);
        if (!task.isDone()) {
            throw new DukeException("Oops! This task is not marked as done yet.");
        }
        task.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.getStatusIcon());
    }

    /**
     * Searches for tasks in the task list that contain the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of tasks that match the search criteria.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
