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
    public void iterate() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
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
    public void markTask(int index) {
        Task taskToMark = tasks.get(index);
        taskToMark.setMarked(true);
        System.out.println("____________________________________________________________"
                + "\nNice! I've marked this task as done:\n" + taskToMark.toString()
                + "\n" + "____________________________________________________________");
    }

    /**
     * Marks a task in the TaskList as not done.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void unmarkTask(int index) {
        Task taskToUnmark = tasks.get(index);
        taskToUnmark.setMarked(false);
        System.out.println("____________________________________________________________"
                + "\nOK, I've marked this task as not done yet:\n" + taskToUnmark.toString()
                + "\n" + "____________________________________________________________");
    }

}
