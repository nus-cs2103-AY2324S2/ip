public class Task {
    protected String description;
    protected boolean isDone;
    private static int numOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numOfTasks++;

    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public static int getNumOfTasks() {
        return numOfTasks;
    }

    public void setStatus() {
        this.isDone = !isDone;
    }

    public String toString() {
        return ("[" + this.getStatusIcon() + "]"  + " " + this.description);
    }
}
