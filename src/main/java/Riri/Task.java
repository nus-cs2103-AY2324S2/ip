package Riri;

public class Task {
    private String task;
    private boolean isDone;
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }
    /**
     * Marks the task as done if complete
     */
    public void markDone() {
        this.isDone = true;
        return;
    }
    /**
     * Marks the task as undone if incomplete
     */
    public void markUndone() {
        this.isDone = false;
    }
    /**
     * Function to help check if a task is done
     * @return true if task is done, false otherwise
     */
    public boolean done() {
        return this.isDone;
    }
    /**
     * Function returns the status of the object
     * @return X if task is done, otherwise " "
     */
    public String getStatus() {
        return (this.isDone) ? "X" : " ";
    }
    @Override
    public String toString() {
        return "["+this.getStatus()+"] "+this.task;
    }
}
