package Quacky;

public abstract class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * This method returns a value of the status for a given task
     * @return 0 if the task is done and 1 otherwise
     */
    public int getStatusValue() {
        return (isDone ? 0 : 1);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",this.getStatusIcon(), description);
    }

    public void markDone() {
        isDone = true;
    }
    public void unmarkDone() {
        isDone = false;
    }

    /**
     * This method is used to return a string that is easy to parse to recover the tasks data
     * @return the parsible string representation of the task
     */
    protected String toFileString() {
       return getStatusValue() +  " | " + this.description;
    };

}