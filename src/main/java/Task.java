public class Task {
    private final String description;

    Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "added: " + this.description;
    }
}
