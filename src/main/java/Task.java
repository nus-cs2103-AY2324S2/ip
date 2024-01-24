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

    public void markAsDone() {
        this.isDone = true;
        System.out.println(String.format("Wow! you finally did it huh ..:\n [%s] %s",this.getStatusIcon(),
                this.getDescription()));
    }

    public void maskAsUndone() {
        this.isDone = false;
        System.out.println(String.format("Hurry up and do it!:\n [%s] %s",this.getStatusIcon(),
                this.getDescription()));
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTypeIcon() {
        return " ";
    }
}