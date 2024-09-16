package fredricksen.tasks;

import java.util.ArrayList;

/**
 * Represents an ArrayList of Task type tasks.
 */
public class TaskList {
    private ArrayList<Task> lists;

    /**
     * Constructs a new TaskList object with a new ArrayList.
     */
    public TaskList() {
        this.lists = new ArrayList<>();
    }


    /**
     * Constructs a TaskList object with a specified list of Task type tasks.
     *
     * @param lists An ArrayList of Task type objects.
     */
    public TaskList(TaskList lists) {
        this.lists = lists.getList();
    }

    /**
     * Add a new Task to the ArrayList list.
     *
     * @param task The Task to be added to the ArrayList.
     */
    public void addTask(Task task) {
        this.lists.add(task);
    }

    /**
     * Remove a Task from the ArrayList based on the position in the ArrayList.
     * @param task The index of the Task in the ArrayList.
     */
    public void deleteTask(int task) {
        this.lists.remove(task);
    }

    /**
     * Returns the Task in the ArrayList based on their position in the ArrayList.
     *
     * @param index The index of the Task in the ArrayList.
     * @return A Task in the ArrayList.
     */
    public Task getTask(int index) {
        return this.lists.get(index);
    }

    public ArrayList<Task> getList() {
        return this.lists;
    }

    /**
     * Returns the current size of the ArrayList.
     *
     * @return an int that represents the size of the ArrayList currently.
     */
    public int size() {
        return this.lists.size();
    }

    /**
     * Clears the entire ArrayList when called.
     */
    public void clearList() {
        this.lists.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : this.lists) {
            sb.append(task.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
