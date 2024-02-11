package Panna;

import java.util.ArrayList;

/**
 * Encapsulates the tasks in the list at an instant of time
 */
public class TaskList  {
    private ArrayList<Task> tasks;

    /**
     * Constructor method for TaskList which initializes the array.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Prints the list out in a systematic organized manner
     */
    public void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1 + ". " + tasks.get(i));
        }
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
            return this.tasks.get(i);
        }
        catch (Exception e) {
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
            tasks.remove(label);
        }
        catch (Exception e) {
            throw new PannaException("Invalid label! The number of tasks now is " + this.size() +
                    "\nPlease try with a more appropriate value! ");
        }

    }
}
