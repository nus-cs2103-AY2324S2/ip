package eve.tasks;
/*
 * This object is what is stored in the list
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String tag;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getTaskStatus() {
        return isDone;
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public int getStatusInteger() {
        return (isDone ? 1 : 0); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return description;
    }

    public String toStore() {
        return description;
    }

    public String getTask() {
        return description;
    }

    public void addTag(String tag) {
        this.tag = tag;
    }
}
