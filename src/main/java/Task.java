public class Task {

    private String description;
    private boolean done;
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void complete() {
        this.done = true;
    }

    public void incomplete() {
        this.done = false;
    }

    @Override
    public String toString() {
        String done = this.done ? "[X]": "[ ]";
        return done + " " + this.description;
    }
}
