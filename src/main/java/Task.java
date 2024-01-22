public class Task {
    private String title;

    public Task(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
