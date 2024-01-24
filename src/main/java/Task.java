public class Task {
    private String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void mark() {
        this.isDone = true;
        System.out.println("__________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + this.toString()
                + "\n__________________________________________________________\n");
    }

    public void unmark() {
        this.isDone = false;
        System.out.println("__________________________________________________________\n"
                + "Nice! I've marked this task as not done yet:\n"
                + this.toString()
                + "\n__________________________________________________________\n");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
