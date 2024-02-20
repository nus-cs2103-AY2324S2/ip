package panna;

import java.util.ArrayList;

/**
 * Encapsulates the tasks in the list at an instant of time
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor method for TaskList which initializes the array.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Prints the list out in a systematic organized manner
     */
    public String printList() {
        String s = "";
        for (int i = 0; i < tasks.size(); i++) {
            s = s + (i + 1 + ". " + tasks.get(i) + "\n");
        }
        return s;
    }

    /**
     * Adds a Task t to the list
     * @param t
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Gives the size of the list
     * @return size
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the list element in the i-th position
     * @param i
     * @return list element in ith position
     * @throws PannaException
     */
    public Task get(int i) throws PannaException {
        try {
            return tasks.get(i);
        } catch (Exception e) {
            throw new PannaException("Index out of bounds!");
        }
    }

    /**
     * Deletes the list element in the ith position
     * @param label
     * @throws PannaException
     */
    public void delete(int label) throws PannaException {
        try {
            int length = tasks.size();
            tasks.remove(label);
            assert(tasks.size() == length - 1);
        } catch (Exception e) {
            throw new PannaException("Invalid label! The number of tasks now is "
                    + size()
                    + "\nPlease try with a more appropriate value! ");
        }

    }
}
