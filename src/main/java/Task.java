public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, int isDone) {
        this.description = description;
        this.isDone = isDone != 0;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public int getIsDone(){
        return isDone ? 1 : 0;
    }

    public String listTaskString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public abstract char getTaskType();

}