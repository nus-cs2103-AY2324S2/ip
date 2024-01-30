public class Task {
    protected String name;
    protected boolean completed;

    //constructor
    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public void markTask() {
        this.completed = true;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public void unmarkTask() {
        this.completed = false;
    }

    public String getStatusIcon() {

        return (completed ? "X" : " "); // mark done task with X
    }

    public String toWrite() {
        return (completed ? "1" : "0") + " | " + name;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + name;
    }
}
