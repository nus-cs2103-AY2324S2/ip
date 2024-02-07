package duke.task;


/**
 * Superclass of all task types. Contains the constructor for defining a task's name and type
 */

public class Task {
    String name;
    String type;
    boolean isComplete;
    public Task(String name, String type) {
        this.name = name;
        this.type = type;
        this.isComplete = false;
    }
//<<<<<<< HEAD

    /**
     * Sets a task's completion status as true
     */

    public void setComplete() {
        this.isComplete = true;
    }

    /**
     * Sets a task's completion status as false
     */
    public void setInComplete() {
        this.isComplete = false;
    }
}