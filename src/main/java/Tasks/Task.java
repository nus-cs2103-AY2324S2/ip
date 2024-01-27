package Tasks;

public abstract class Task {
    protected String description;
    private boolean completed;
    public Task(String task) {
        this.description = task;
        this.completed = false;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public void markUncompleted() {
        this.completed = false;
    }
    public boolean isCompleted() { return this.completed; }
    abstract public String save();

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (this.completed) {
            sb.append("X");
        } else {
            sb.append(" ");
        }
        sb.append("]");
        sb.append(" " + this.description);
        return sb.toString();
    }
}
