package Tasks;

public class Task {
    protected String task;
    protected boolean completed;

    public Task(String task) {
        this.task = task;
    }

    public void markTask() {
        this.completed = true;
    }
    public void unmarkTask() {
        this.completed = false;
    }

    @Override
    public String toString() {
        String check = completed ? "[X]" : "[ ]";
        return check + " " + task;
    }
}
