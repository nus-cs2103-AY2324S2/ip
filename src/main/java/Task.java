public class Task {
    private String task;
    private boolean isDone = false;
    public Task(String task) {
        this.task = task;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String marked () {
        if(isDone) {
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    public String getTask() {
        return task;
    }

}
