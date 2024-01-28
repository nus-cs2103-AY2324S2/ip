public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        return String.format(" T | %s | %s",
                getStatusIcon(),
                description);
    }
}