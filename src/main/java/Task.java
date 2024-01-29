public class Task {
    private String description;
    protected boolean isDone;

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
        System.out.println("__________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + toString()
                + "\n__________________________________________________________\n");
    }

    public void unmark() {
        isDone = false;
        System.out.println("__________________________________________________________\n"
                + "OK, I've marked this task as not done yet:\n"
                + toString()
                + "\n__________________________________________________________\n");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
