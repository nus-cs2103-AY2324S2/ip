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

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
//        System.out.printf("  [%s] %s\n", this.getStatusIcon(), this.getDescription());
        System.out.println("  " + this.toString());
    }

    public void unmark() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet");
//        System.out.printf("  [%s] %s\n", this.getStatusIcon(), this.getDescription());
        System.out.println("  " + this.toString());
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
