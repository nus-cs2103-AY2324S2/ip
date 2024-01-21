class Task {
    private String taskName;
    private boolean isDone;

    public Task(String n) {
        this.taskName = n;
        this.isDone = false;
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