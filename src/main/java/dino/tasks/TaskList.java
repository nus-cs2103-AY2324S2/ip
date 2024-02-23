package dino.tasks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class represents a list of tasks and provides methods for managing the tasks.
 */
public class TaskList implements Serializable {
    private static final long serialVersionUID = 5L;

    private final List<Task> tasks = new ArrayList<>();


    /**
     * The function checks if a list of tasks is empty.
     *
     * @return The method is returning a boolean value.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * The size() function returns the number of tasks in a collection.
     * 
     * @return The size of the tasks list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * The function returns the task at the specified index from a list of tasks.
     * 
     * @param i The parameter "i" is an integer representing the index of the task to retrieve from the "tasks" list.
     * @return The method is returning a Task object.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * The add() function adds a Task object to a list of tasks.
     * 
     * @param t The parameter "t" is of type "Task".
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * The remove function removes an element at the specified index from a list.
     * 
     * @param i The parameter "i" is an integer representing the index of the element to be removed from the "tasks"
     * list.
     */
    public void remove(int i) {
        tasks.remove(i);
    }
}