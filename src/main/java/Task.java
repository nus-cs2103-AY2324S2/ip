public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    public void markDone() {
        if (this.isDone) {
            System.out.println("Stop yappin' bruh... Task is already marked as done");
        } else {
            this.isDone = true;
            System.out.println("Good job Yapper! I've marked this task as done:");
        }
        System.out.println(this);
    }

    public void unmarkDone() {
        if (!this.isDone) {
            System.out.println("Stop yappin' bruh... Task remains incomplete");
        } else {
            this.isDone = false;
            System.out.println("YAPYAP! I've unmarked this task...");
        }
        System.out.println(this);
    }
}
