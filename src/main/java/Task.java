public class Task {
    private String description;
    private int index;

    public Task(int index, String description) {
        this.index = index;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.index + ". " + this.description;
    }
}
