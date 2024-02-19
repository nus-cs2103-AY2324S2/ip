package signal.task;

import java.time.LocalDate;

public class Task {
    private String description;
    private boolean isDone = false;
    private String type;
    private String date;

    public Task(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }
    public boolean checkDone() {
        return this.isDone;
    }
    public String getStatusIcon() {
        return (this.isDone ? "X" : "0" ); // mark done task with 1
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUnDone() {
        this.isDone = false;
    }

    public String checkType() {
        return "Task";
    }
    public String toString() {
        return " | " + getStatusIcon() + " | " + this.description;
    }


    public LocalDate getDue() {
        return null;
    }

}
