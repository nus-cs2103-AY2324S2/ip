public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void changeStatus(String cmd) {
        if (cmd.equals("mark")) {
            isDone = true;
        } else if (cmd.equals("unmark")) {
            isDone = false;
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
