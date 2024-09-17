package meanduke.tasks;

import java.util.ArrayList;

/**
 * This class represents a list of Tasks of up to 100 Tasks
 */
public class TaskList implements Savable {
    private static final int VISUAL_INDEX_OFFSET = 1;

    private final ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with initial capacity of 100
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
    }

    /**
     * Adds a task to the back of the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks the task at the given index as done
     *
     * @param index the index of the task to be marked done
     * @return true if task was already done, else false
     */
    public boolean markDone(int index) {
        return this.tasks.get(index).markDone();
    }

    /**
     * Marks the task at the given index as not done
     *
     * @param index the index of the task to be marked not done
     * @return true if task was already not done, else false
     */
    public boolean unmarkDone(int index) {
        return this.tasks.get(index).unmarkDone();
    }

    /**
     * Removes the Task at the given index from this list
     *
     * @param index The index of the Task to be removed
     * @return The String representation of the removed Task
     */
    public String delete(int index) {
        String ret = this.tasks.get(index).toString();
        this.tasks.remove(index);
        return ret;
    }

    /**
     * Filters the TaskList for Tasks containing the specified String in its fields. Not case-sensitive.
     *
     * @param filterString String to filter the TaskList by.
     * @return String representation of the TaskList containing only Tasks who contain filterString.
     */
    public String filter(String filterString) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            String taskString = this.tasks.get(i).toString();
            if (taskString.toUpperCase().contains(filterString.toUpperCase())) {
                sb.append((i + VISUAL_INDEX_OFFSET) + ". " + this.tasks.get(i) + "\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String saveString() {
        String ret = "";
        for (Task t : this.tasks) {
            assert !t.saveString().isEmpty();
            ret = ret + t.saveString() + "\n";
        }
        return ret;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append((i + VISUAL_INDEX_OFFSET) + ". " + this.tasks.get(i) + "\n");
        }
        return sb.toString();
    }
}
