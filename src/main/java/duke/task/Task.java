package duke.task;

/**
 * Superclass of all task types. Contains the constructor for defining a task's name and type
 */
public class Task {
    private String name;
    private String type;
    private boolean isComplete;

    /**
     * superclass constructor for new Task object
     * @param name  name of task
     * @param type  type of task
     */
    public Task(String name, String type) {
        this.name = name;
        this.type = type;
        this.isComplete = false;
    }

    public String getName() {
        return this.name;
    }
    public boolean isComplete() { return this.isComplete; }
    public String getType() { return this.type; }
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
