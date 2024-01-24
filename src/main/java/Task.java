public class Task {
    private final String name;

    Task(String name) {
        this.name =name;
    }
    public String toString() {
        return String.format("%s", this.name);
    }
}
