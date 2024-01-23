import enums.TaskStatus;

class Task {
    private String description;
    private TaskStatus isDone;

    public Task(String description, TaskStatus isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return this.isDone.name().equals("COMPLETE") ? "[X] " + this.description : "[ ] " + this.description;
    }

    public String getDescription() {
        return this.description;
    }

    public TaskStatus getIsDone() {
        return this.isDone;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setIsDone(TaskStatus newIsDone) {
        this.isDone = newIsDone;
    }
}
