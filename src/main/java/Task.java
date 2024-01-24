public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public void displayMessage(int currSize) {
        Reply replyToUser = new AddToListReply(this.description, currSize);
        replyToUser.displayMessage();
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
