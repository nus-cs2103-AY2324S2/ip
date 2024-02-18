package bob;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * A class that encapsulates a list of tasks.
 */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> tasks;

    /*
     * A constructor that creates a new task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /*
     * A constructor that creates a new task list out of the given array list.
     */
    public TaskList(ArrayList<Task> arrayList) {
        this.tasks = arrayList;
    }

    /*
     * This method returns the size of the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /*
     * This method adds a task into the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /*
     * This method removes a task from the list.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /*
     * This method retrieves the task from the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /*
     * This method clears all tasks from the list.
     */
    public void clearTasks() {
        tasks.clear();
    }

    /*
     * This method checks if the list is empty.
     *
     * @return A boolean representing whether the task list is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /*
     * An Iterator method that goes through each task in the list.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    /*
     * A method to check if the contents of the task lists are equal.
     *
     * @parameter o The object that you want to compare.
     * @return A boolean representing whether the two objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof TaskList)) {
            return false;
        }

        TaskList other = (TaskList) o;

        if (this.size() != other.size()) {
            return false;
        }

        for (int i = 0; i < this.size(); i++) {
            if (!this.getTask(i).equals(other.getTask(i))) {
                return false;
            }
        }

        return true;
    }
}
