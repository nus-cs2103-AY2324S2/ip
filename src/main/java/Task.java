public class Task {
    protected String description;
    protected boolean isDone;

    protected String type = "";
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void done() {
        isDone = true;
    }

    public void undone() {
        isDone = false;
    }

    public void print() {

    }

    public String type(){
        return type;
    }
    //...
}
