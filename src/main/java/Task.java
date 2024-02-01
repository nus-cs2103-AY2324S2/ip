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

    public boolean getStatus() {
        return isDone;
    }

    public void changeStatus() {
        isDone = !isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getDisplayedString() {
        return getStatusIcon() + " " + getDescription();
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

    public String serializeTask() {
        return null;
    }
}
