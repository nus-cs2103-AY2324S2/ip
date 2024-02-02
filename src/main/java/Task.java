public class Task implements FileFormattable {
    private final String description;
    private boolean isDone;
    public Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public int check() {
        if (this.isDone) {
            return -1;
        } else {
            this.isDone = true;
            return 0;
        }
    }
    public int uncheck() {
        if (!this.isDone) {
            return -1;
        } else {
            this.isDone = false;
            return 0;
        }
    }
    public String getDescription() {
        return this.description;
    }
    public String toFileFormat() {
        return String.format("%d |&| %s", this.isDone ? 1 : 0, this.description);
    }
    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? 'X' : ' ', this.description);
    }
}
