package badgpt.exceptions;

import badgpt.tasks.Task;

public class SameStatusException extends TaskException {
    private Task task;
    private int type;

    public SameStatusException(String message, Task task, int type) {
        super(message);
        this.task = task;
        this.type = type;
    }

    @Override
    public String toString() {
        return "The task:\n" + this.task + "\nis " + (this.type == 0 ? "already complete." : "not yet complete.");
    }
}
