

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

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setIsDone(boolean newIsDone) {
        this.isDone = newIsDone;
    }
}
