public class Task {
    private final String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getDone() {
        return this.done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        String addMark = this.getDone() ? "X" : " ";
        return String.format("[%s] %s", addMark, this.getDescription());
    }
}
