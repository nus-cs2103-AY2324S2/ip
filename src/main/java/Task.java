public abstract class Task implements CsvFormat {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getDone() {
        return this.done;
    }

    public void done() {
        this.done = true;
    }

    public void undone() {
        this.done = false;
    }

    @Override
    public String toString() {
        return "[" + (this.done ? "X" : " ") + "] " + this.description;
    }
}
