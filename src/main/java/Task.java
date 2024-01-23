public class Task {
    private final String description;

    public Task(String token) {
        this.description = token;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
