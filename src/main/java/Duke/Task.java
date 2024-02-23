package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String checkbox;
    private final String msg;

    public Task (String msg) {
        this.msg = msg;
        this.checkbox = "[ ]";
    }

    public void markTask() {
        this.checkbox = "[X]";
    }

    public void unmarkTask() {
        this.checkbox = "[ ]";
    }

    public boolean compareMsg(Task task) {
        return this.msg.equals(task.msg);
    }

    public abstract boolean isDuplicate(Task task);

    public String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return checkbox + "  " + msg;
    }
}
