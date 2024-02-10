package slaybot;

import java.util.ArrayList;
import java.util.List;

import entity.Task;

/**
 * The TaskList class represents a collection of tasks in the SlayBot application.
 * It provides methods to manipulate and interact with the list of tasks, such as adding,
 * removing, marking, and searching for tasks.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Iterates through the tasks in the TaskList and prints their information.
     */
    public String iterate() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output += i + 1 + ". " + tasks.get(i) + "\n";
        }

        return output;
    }

    /**
     * Finds tasks in the TaskList that contain the specified search value in their description.
     *
     * @param searchValue The search value to match against task descriptions.
     * @return A list of tasks matching the search criteria.
     */
    public List<Task> findTasks(String searchValue) {
        List<Task> result = new ArrayList<>();

        for (Task t : this.tasks) {
            if (t.toString().contains(searchValue)) {
                result.add(t);
            }
        }

        return result;
    }

    /**
     * Marks a task in the TaskList as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public Task markTask(int index) {
        Task taskToMark = tasks.get(index);
        taskToMark.setMarked(true);
        return taskToMark;
    }

    /**
     * Marks a task in the TaskList as not done.
     *
     * @param index The index of the task to be marked as not done.
     */
    public Task unmarkTask(int index) {
        Task taskToUnmark = tasks.get(index);
        taskToUnmark.setMarked(false);
        return taskToUnmark;
    }

}
