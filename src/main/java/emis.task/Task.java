package main.java.emis.task;
import main.java.emis.Ui;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
        Ui.showLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t[" + this.getStatusIcon() + "] " + this.description);
        Ui.showLine();
    }

    public void markAsUndone() {
        this.isDone = false;
        Ui.showLine();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t\t[" + this.getStatusIcon() + "] " + this.description);
        Ui.showLine();
    }

    public String storeString() {
        // done = 1, not done = 0
        int status = isDone ? 1 : 0;
        return status + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}