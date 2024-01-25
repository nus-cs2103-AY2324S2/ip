public class Task {
    private final String description;
    private boolean done;

    Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String toString() {
        String checkBox = this.done ? "[X]" : "[]";
        return checkBox + " " + this.description;
    }
}
