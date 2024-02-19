package model;

import java.io.Serializable;

/**
 * The {@code Task} interface represents the basic functionality of a task.
 * 
 * <p>Each {@code Task} is able to be set as complete or incomplete.
 */
public interface Task extends Serializable {
    /**
     * Marks the task as complete.
     * 
     * @return {@code Task} object with mark set as complete.
     */
    public Task mark();

    /**
     * Marks the task as incomplete.
     * 
     * @return {@code Task} object with mark set as incomplete.
     */
    public Task unmark();

    public boolean nameContains(String s);

    public Task tag(String s);
}
