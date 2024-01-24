/**
 * Encapsulate a Task with a name and status of boolean to represent whether it has been completed.
 *
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */

public class Task {
    /** A unique int to identify the Task. */
    int id; // in case we need it in future

    /** A boolean value that states whether the Task has been completed or not. */
    private boolean completed=false;

    /** A String value that states the name of the Task. */
    private String name;

    /**
     * Constructor for the Task.
     * @param name A String value that states the name of the Task.
     */
    public Task(String name){
        this.name=name;
    }

    /**
     * Constructor for the Task.
     * @param name A String value that states the name of the Task.
     * @param completed A boolean value that states whether the Task has been completed or not.
     */
    public Task(String name, boolean completed){
        this.name = name;
        this.completed = completed;
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
     * @return Returns the String representation of a Task.
     */
    @Override
    public String toString() {
        String mark = completed?"X":" ";
        return "["+mark+"]"+" "+name;
    }
}
