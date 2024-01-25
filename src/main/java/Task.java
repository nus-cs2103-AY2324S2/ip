public class Task {
    protected String description;
    protected boolean isDone;
    private static int nextId = 1;
    private int id;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.id = nextId++;
    }

    public int getTaskId() {
        return id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
