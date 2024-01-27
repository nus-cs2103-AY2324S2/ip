class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String n) {
        this.taskName = n;
        this.isDone = false;
    }

    public Task(String n, boolean d) {
        this.taskName = n;
        this.isDone = d;
    }

    @Override
    public String toString() {
        String d = (this.isDone) ? "X" : " ";
        return String.format("[%s] %s", d, taskName);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }
}