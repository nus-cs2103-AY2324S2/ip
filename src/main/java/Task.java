public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusUpdate() {
        return (isDone ? "[X] " : "[ ] ") + this.description ;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done: \n" + this.getStatusUpdate());
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet: \n" + this.getStatusUpdate());
    }
}