public class Task {
    protected String description;
    protected int id;
    protected boolean isDone;

    public Task(String description, int id) {
        this.description = description;
        this.isDone = false;
        this.id = id;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getFullStatus() {
        return this.id + ". [" + this.getStatusIcon() + "] " + this.description;
    }

    public void setDone(boolean doneness) {
        this.isDone = doneness;
    }

}

