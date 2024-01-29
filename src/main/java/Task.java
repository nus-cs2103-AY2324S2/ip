public class Task {
    private String done;
    private String msg;

    public Task (String msg) {
        this.msg = msg;
        this.done = "[ ]";
    }

    public void markTask() {
        this.done = "[X]";
    }

    public void unmarkTask() {
        this.done = "[ ]";
    }

    @Override
    public String toString() {
        return done + "  " + msg;
    }
}
