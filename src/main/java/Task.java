public abstract class Task {
    protected Boolean done;
    protected String taskContent;

    public Task(String input) {
        this.taskContent = input;
        this.done = false;
    }
    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }
}
