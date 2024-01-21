public class Task {
    private String name;
    private Boolean isDone;
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Boolean getDone() {
        return this.isDone;
    }
    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
