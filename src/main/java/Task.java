public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String timeprint() {
        return "";
    }

    public String typeid() {
        return "T";
    }

    public String getDescription() {
        return description;
    }

    public String markstatus() {
        if (isDone) {
            return "marked";
        } else {
            return "unmarked";
        }
    }

    public String toString() {
        return ("["+ this.getStatusIcon()+"] " + this.description);
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    //...
}