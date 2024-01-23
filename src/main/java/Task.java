

class Task {
    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return this.isDone ? "[X] " + this.description : "[] " + this.description;
    }
}
