public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void setToDone() {
        isDone = true;
    }

    public void mark() {
        isDone = true;
        System.out.println(Ui.LINE + "Nice! I've marked this task as done:\n" + toString() + Ui.LINE);
    }

    public void unmark() {
        isDone = false;
        System.out.println(Ui.LINE + "OK, I've marked this task as not done yet:\n" + toString() + Ui.LINE);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
