public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getDisplayedString() {
        throw new RuntimeException();
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Mission completed:\n");
        System.out.println(" " + this.getStatusIcon() + " " + this.getDescription());
    }
    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("Mission pending:\n");
        System.out.println(" " + this.getStatusIcon() + " " + this.getDescription());
    }
}
