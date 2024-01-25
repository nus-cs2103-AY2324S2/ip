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

    public void changeStatus(Command cmd) {
        if (cmd.equals(Command.MARK)) {
            isDone = true;
        } else if (cmd.equals(Command.UNMARK)) {
            isDone = false;
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
