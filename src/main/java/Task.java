public abstract class Task {
    private String task;
    private boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public String printTask() {
        return String.format("[" + (this.done ? "X" : " ") + "] " + this.task);
    }

    public void done() {
        this.done = true;
    }

    public void undo() {
        this.done = false;
    }
}
