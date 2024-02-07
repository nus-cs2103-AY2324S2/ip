public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        String done = (this.isDone ? "X" : "");
        return "[" + done + "] ";
    }

    public String mark() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        return this.toString();
    }

    public String unmark() {
        System.out.println("OK, I've marked this task as not done yet:");
        this.isDone = false;
        return this.toString();
    }

    @Override
    public String toString() {
        return getStatusIcon() + this.description;
    }
}
