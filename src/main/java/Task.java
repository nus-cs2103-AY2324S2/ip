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

//    public String getDescription() {
//        return this.description;
//    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public void displayMessage() {
        Reply replyToUser = new AddToListReply(this.description);
        replyToUser.displayMessage();
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
