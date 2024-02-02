package tasks;

import exceptions.tasks.EmptyDescriptionException;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) throws EmptyDescriptionException {
        this.setDescription(description);
        this.isDone = false;
    }

    public Task(String description, boolean isDone) throws EmptyDescriptionException {
        this.setDescription(description);
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    private String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    private void setDescription(String description) throws EmptyDescriptionException {
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        this.description = description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
