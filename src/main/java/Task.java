public class Task {

    private Boolean done;
    private final String name;
    Task(String name) {
        this.done = false;
        this.name = name;
    }

    // Mark task as done
    public void mark() {
        this.done = true;
    }

    // Unmark task
    public void unmark() {
        this.done = false;
    }
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + this.name + "\n";
        } else {
            return "[] " + this.name + "\n";
        }
    }
}
