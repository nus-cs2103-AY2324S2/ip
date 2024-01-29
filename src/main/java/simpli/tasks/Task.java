package simpli.tasks;

public class Task {
    private final String name;
    private boolean isDone;

    public Task(boolean isDone, String name) {
        this.name = name;
        this.isDone = isDone;
    }

    public void done() {
        this.isDone = true;
    }

    public void undone() {
        this.isDone = false;
    }

    public String toCsv() {
        return String.format("%s,%s", isDone ? 1 : 0, name);
    }

    public String toString() {
        return String.format(
                "[%c] %s",
                this.isDone ? 'X' : ' ',
                this.name
        );
    }
}
