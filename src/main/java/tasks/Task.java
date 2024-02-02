package tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getStatus() {
        return this.isDone;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    public void markDone(boolean start) {
        if (this.isDone) {
            System.out.println("Stop yappin' bruh... tasks.Task is already marked as done");
        } else {
            this.isDone = true;
            if (!start) {
            System.out.println("Good job Yapper! I've marked this task as done:");
            }
        }
        if (!start) {
            System.out.println(this);
        }
    }

    public void unmarkDone() {
        if (!this.isDone) {
            System.out.println("Stop yappin' bruh... tasks.Task remains incomplete");
        } else {
            this.isDone = false;
            System.out.println("YAPYAP! I've unmarked this task...");
        }
        System.out.println(this);
    }

    public String toFileFormat() {
        return "";
    }

}
