public class Task {

    private String description;
    private boolean done = false;

    public Task(String description) {
        this.description = description;
    }

    public void updateStatus(boolean state) {
        this.done = state;
    }

    public String getStatus() {
        return "[" + (done ? "X" : " ") + "]";
    }

    @Override
    public String toString() {
        return this.description;
    }
}
