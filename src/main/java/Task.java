public class Task {
    private final String description;
    private boolean status;

    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public Task(String description, boolean status) {
        this.description = description;
        this.status = status;
    }

    public void setStatus(boolean done) {
        this.status = done;
    }

    public String stringify() {
        String done = "[ ]";
        if (this.status) {
            done = "[X]";
        }
        return done + " " + this.description;
    }

    public String toString() {
        String done = "O";
        if (this.status) {
            done = "X";
        }
        return done + " | " + this.description;
    }

}