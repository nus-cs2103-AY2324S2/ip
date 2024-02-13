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
        setDone();
        System.out.println("Nice! I've marked this task as done:");
        return this.toString();
    }

    public void setDone() {
        this.isDone = true;
    }

    public String unmark() {
        System.out.println("OK, I've marked this task as not done yet:");
        setUndone();
        return this.toString();
    }

    public void setUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + this.description;
    }

    public String saveToFile() {
        return (this.isDone ? "1" : "0")
                + "!" + this.description;
    }
}
