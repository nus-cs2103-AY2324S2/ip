public class Task {
    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public void complete() {
        this.completed = true;
    }

    public boolean isComplete() {
        return this.completed;
    }

    public String printName() {
        return this.name;
    }
}
