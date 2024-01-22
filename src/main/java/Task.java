/**
 * Class representing a user Task.
 */
public class Task {

    private String description;
    private boolean done = false;

    public Task(String description) {
        this.description = description;
    }

    public void updateStatus(boolean state) {
        this.done = state;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public String getStatus() {
        return "[" + (done ? "X" : " ") + "]";
    }

    public String getType() {
        return "[T]";
    }
}
