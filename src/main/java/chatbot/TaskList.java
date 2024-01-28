package chatbot;

import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



/**
 * The TaskList class manages a list of tasks. It provides methods to manipulate tasks
 * such as adding, removing, and marking them as done or not done. It also allows accessing
 * individual tasks and the entire list of tasks.
 */

public class TaskList {

    protected ArrayList<Task> al;

     /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param al The initial list of tasks.
     */
    public TaskList(ArrayList<Task> al) {
        this.al = al;
    }
    /**
 * Adds a task to the task list.
 *
 * @param t The task to be added.
 */
    public void add(Task t) {
        al.add(t);
    }
/**
 * Removes a task from the task list at the specified index.
 *
 * @param c The index (1-based) of the task to be removed.
 */
    public void remove(int c ) {
        al.remove(c);
    }
/**
 * Marks a task as done.
 *
 * @param c The index (1-based) of the task to be marked as done.
 */
    public void asDone(int c) {
        al.get(c-1).markAsDone();
    }
/**
 * Marks a task as not done.
 *
 * @param c The index (1-based) of the task to be marked as not done.
 */
    public void asNotDone(int c) {
        al.get(c-1).markAsUndone();
    }
/**
 * Retrieves a task from the task list at the specified index.
 *
 * @param c The index (1-based) of the task to retrieve.
 * @return The task at the specified index.
 */
    public Task get(int c) {
        return al.get(c);
    }
/**
 * Returns the current list of tasks.
 *
 * @return The list of tasks.
 */
    public ArrayList<Task> mine() {
        return this.al;
    }
/**
 * Returns the number of tasks in the task list.
 *
 * @return The size of the task list.
 */
    public int len() {
        return this.al.size();
    }

}

