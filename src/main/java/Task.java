public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + this.description ;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done: \n" + this.toString());
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet: \n" + this.toString());
    }
}