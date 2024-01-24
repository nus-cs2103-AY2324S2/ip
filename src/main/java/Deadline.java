public class Deadline extends Task {
    private String deadlineTime;

    public Deadline (String description, String deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    public void displayMessage(int currSize) {
        Reply replyToUser = new AddToListReply(this.toString(), currSize);
        replyToUser.displayMessage();
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineTime + ")";
    }
}
