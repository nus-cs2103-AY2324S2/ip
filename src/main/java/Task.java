public class Task {
    protected String title;
    protected boolean isDone;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public void isDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String isDoneIcon = isDone ? "X" : " ";
        return String.format("[%s] %s", isDoneIcon, title);
    }
}
