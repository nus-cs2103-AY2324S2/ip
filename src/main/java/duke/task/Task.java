package duke.task;


/**
 * Superclass of all task types. Contains the constructor for defining a task's name and type
 */

public class Task {
    String name;
    String type;
    boolean complete;
    public Task(String name, String type) {
        this.name = name;
        this.type = type;
        this.complete = false;
    }

    /**
     * Sets a task's completion status as true
     */
    public void mark() {
        this.complete = true;
    }

    /**
     * Sets a task's completion status as false
     */
    public void unmark() {
        this.complete = false;
    }
}