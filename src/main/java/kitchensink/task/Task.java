package kitchensink.task;

import kitchensink.Ui;
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void setToDone() {
        isDone = true;
    }

    public void mark(Ui ui) {
        isDone = true;
        ui.sayTaskMarked(this);
    }

    public void unmark(Ui ui) {
        isDone = false;
        ui.sayTaskUnmarked(this);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
