package cappy.task;

import cappy.storage.CsvFormat;

public abstract class Task implements CsvFormat {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.done = isDone;
    }

    public String getDescription() {
        return description;
    }

    public boolean getDone() {
        return done;
    }

    public void done() {
        done = true;
    }

    public void undone() {
        done = false;
    }

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "] " + description;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task)) {
            return false;
        }
        Task other = (Task) obj;
        return description.equals(other.description) && done == other.done;
    }
}
