public class Task {
    private boolean isDone;
    private String name;

    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String checkBox = isDone ? "[X]" : "[ ]";
        return String.format("%s %s", checkBox, name);
    }
}
