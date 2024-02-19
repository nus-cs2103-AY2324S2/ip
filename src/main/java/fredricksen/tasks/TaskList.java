package fredricksen.tasks;

import java.util.ArrayList;

/**
 * Represents an ArrayList of Task type tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a new TaskList object with a new ArrayList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }


    /**
     * Constructs a TaskList object with a specified list of Task type tasks.
     *
     * @param list An ArrayList of Task type objects.
     */
    public TaskList(TaskList list) {
        this.list = list.getList();
    }

    /**
     * Add a new Task to the ArrayList list.
     *
     * @param task The Task to be added to the ArrayList.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Remove a Task from the ArrayList based on the position in the ArrayList.
     * @param task The index of the Task in the ArrayList.
     */
    public void deleteTask(int task) {
        this.list.remove(task);
    }

    /**
     * Returns the Task in the ArrayList based on their position in the ArrayList.
     *
     * @param index The index of the Task in the ArrayList.
     * @return A Task in the ArrayList.
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Returns the current size of the ArrayList.
     *
     * @return an int that represents the size of the ArrayList currently.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Clears the entire ArrayList when called.
     */
    public void clearList() {
        this.list.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : this.list) {
            sb.append(task.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
