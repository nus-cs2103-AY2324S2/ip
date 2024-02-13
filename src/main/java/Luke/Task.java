package Luke;

public class Task {
    protected boolean done;
    protected String name;

    public Task (String name) {
        this.done = false;
        this.name = name;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }

    public void setToDone() {
        this.done = true;
    }

    public void setToNotDone() {
        this.done = false;
    }
}