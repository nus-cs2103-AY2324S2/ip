public class Task {
    String task;
    boolean completed;
    public Task (String task) {
        this.task = task;
        this.completed = false;
    }

    @Override
    public String toString() {
        return this.task;
    }
}
