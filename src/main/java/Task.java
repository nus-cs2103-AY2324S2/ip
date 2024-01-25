/**
 * Encapsulate a Task with a name and status of boolean to represent whether it
 * has been completed.
 *
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */

public class Task {
    /** A String value that states the name of the Task. */
    private String name;

    /** A String value that represent the type of Task. */
    private String type;

    /** A boolean value that states whether the Task has been completed or not. */
    private boolean completed = false;

    /**
     * Constructor for the Task.
     * 
     * @param name A String value that states the name of the Task.
     */
    public Task(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Sets the status of completed to true.
     */
    public void setCompleted() {
        this.completed = true;
    }

    /**
     * Sets the status of completed to false.
     */
    public void setNotCompleted() {
        this.completed = false;
    }

    /**
     * String representation of a Task.
     * 
     * @return Returns the String representation of a Task.
     */
    @Override
    public String toString() {
        String mark = completed ? "X" : " ";
        return "[" + this.type + "]" + "[" + mark + "]" + " " + this.name;
    }
}
