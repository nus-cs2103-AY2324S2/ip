
package duke.task;


/**
 * Contatins the information of the Task.
 */

public abstract class Task {

    private String description;
    private boolean isDone;


    /**
     * Constructs the task object.
     * @param description Contains the description of the event.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }
    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public abstract String toString();

    public String type() {
        return "";
    }

    public boolean checkDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
}
