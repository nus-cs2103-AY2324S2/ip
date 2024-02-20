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

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Removes a Task from the TaskList by the given index.
     *
     * @param index The index of the task to be removed.
     */
    public String removeTask(int index) {
        try {
            this.tasks.remove(index);
            String deleteText = "Successful deletion \n You now have "
                    + tasks.size() + " tasks";
            Storage.saveTasks(this);
            return deleteText;
        } catch (IndexOutOfBoundsException e) {
            return "Please input a valid index";
        }
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
    public String findTasks(String searchValue) {
        List<Task> list = new ArrayList<>();
        String result = "";

        for (Task t : this.tasks) {
            if (t.toString().contains(searchValue)) {
                list.add(t);
            }
        }

        if (list.isEmpty()) {
            return "No Results Found";
        } else {
            for (int i = 0; i < list.size(); i++) {
                result += i + 1 + ". " + list.get(i).toString() + "\n";
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

        assert taskToMark.getMarked() == true;

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

        assert taskToUnmark.getMarked() == false;

        return taskToUnmark;
    }

    public void sortTask() {
        this.tasks.sort(new TaskComparator());
    }

}
