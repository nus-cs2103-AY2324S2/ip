package signal;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : "0" ); // mark done task with 1
    }

    public String toString() {
        return " | " + getStatusIcon() + " | " + this.description;
    }

    public void editDescription(String input) {
        this.description = input;
    }

    public String checkType() {
        return "Task";
    }

    public boolean checkDone() {
        return this.isDone;
    }
    public void markDone() {
        this.isDone = true;
    }

    public void markUnDone() {
        this.isDone = false;
    }

    public LocalDate getDue() {
        return null;
    }

}
