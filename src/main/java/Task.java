public class Task {
    String taskName;
    Boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String done = " ";
        if (this.isDone) {
            done = "X";
        }
        return "[" + done + "] " + this.taskName;
    }

}
