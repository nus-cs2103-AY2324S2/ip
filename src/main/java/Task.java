public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    public String toStringForFile() {
        String d = "";
        if (this.isDone) {
            d = "1";
        } else {
            d = "0";
        }
        return  d + " | " + this.description;
    }
}
