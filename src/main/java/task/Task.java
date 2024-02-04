package task;
/** This class represents a Task in which the User can store and list them in Sir Duke */
public class Task {
    /** Description of Task */
    protected String description;
    /** is true when task isDone */
    protected boolean isDone;

    /**
     * Returns a Task object
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string value of the Task's status of whether it is done or not
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a String value of the task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets isDone to True
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets isDone to False
     */
    public void unMarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns a string value of the Task's status and description
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon()+ "] " + this.description;
    }
}
